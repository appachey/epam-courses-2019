package ua.nure.bychkov.practice7.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.bychkov.practice7.consts.Constants;
import ua.nure.bychkov.practice7.consts.Names;
import ua.nure.bychkov.practice7.entity.Manufacturer;
import ua.nure.bychkov.practice7.entity.Medicine;
import ua.nure.bychkov.practice7.entity.Medicines;
import ua.nure.bychkov.practice7.entity.Version;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMController {
    private String xmlFileName;

    private Medicines medicines;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Medicines getMedicines() {
        return medicines;
    }

    public void parse(boolean validate)
        throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setNamespaceAware(true);

        if (validate) {
            dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();

        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }
        });

        Document document = db.parse(xmlFileName);

        Element root = document.getDocumentElement();

        medicines = new Medicines();

        NodeList medicinesNodes = root.getElementsByTagName(Names.MEDICINE);

        for (int i = 0; i < medicinesNodes.getLength(); i++) {
            Medicine medicine = getMedicine(medicinesNodes.item(i));
            medicines.getMedicines().add(medicine);
        }
    }

    private static Medicine getMedicine(Node mNode) {
        Medicine medicine = new Medicine();
        Element mElement = (Element) mNode;

        Node nameNode = mElement.getElementsByTagName(Names.NAME).item(0);
        Node pharmNode = mElement.getElementsByTagName(Names.PHARM).item(0);
        Node groupNode = mElement.getElementsByTagName(Names.GROUP).item(0);

        medicine.setName(nameNode.getTextContent());
        medicine.setGroup(groupNode.getTextContent());
        medicine.setPharm(pharmNode.getTextContent());

        NodeList analogsList = mElement.getElementsByTagName(Names.ANALOG);
        for (int i = 0; i < analogsList.getLength(); i++) {
            String analog = getAnalog(analogsList.item(i));
            medicine.getAnalogs().add(analog);
        }

        NodeList versionList = mElement.getElementsByTagName(Names.VERSION);
        for (int i = 0; i < versionList.getLength(); i++) {
            Version version = getVersion(versionList.item(i));
            medicine.getVersions().add(version);
        }
        return medicine;
    }

    private static Version getVersion(Node vNode) {
        Version version = new Version();
        Element vElement = (Element) vNode;
        Node typeNode = vElement.getElementsByTagName(Names.TYPE).item(0);
        version.setType(typeNode.getTextContent());
        NodeList manufacturersList = vElement.getElementsByTagName(Names.MANUFACTURER);
        for (int i = 0; i < manufacturersList.getLength(); i++) {
            Manufacturer manufacturer = getManufacturer(manufacturersList.item(i));
            version.getManufacturers().add(manufacturer);
        }
        return version;
    }

    private static Manufacturer getManufacturer(Node manufNode) {
        Manufacturer manufacturer = new Manufacturer();
        Element manufElement = (Element) manufNode;
        Node dosageNode = manufElement.getElementsByTagName(Names.DOSAGE).item(0);
        Node certNode = manufElement.getElementsByTagName(Names.CERTIFICATE).item(0);
        Node packNode = manufElement.getElementsByTagName(Names.PACKAGE).item(0);
        manufacturer.setDosage(Integer.parseInt(dosageNode.getTextContent()));
        String dosageUnit = manufElement.getAttribute(Names.UNIT);
        manufacturer.setDosgeUnit(dosageUnit);
        return manufacturer;
    }

    private static String getAnalog(Node analogNode) {
        Element aElement = (Element) analogNode;
        return aElement.getTextContent();
    }
}
