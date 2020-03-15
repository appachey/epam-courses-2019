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
import ua.nure.bychkov.practice7.entity.*;
import ua.nure.bychkov.practice7.entity.Package;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 *
 */
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
        Node typeNode = vElement.getElementsByTagName(Names.VER_TYPE).item(0);
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

        Node certNode = manufElement.getElementsByTagName(Names.CERTIFICATE).item(0);
        Certificate certificate = getCertificate(certNode);
        manufacturer.setCertificate(certificate);

        Node packNode = manufElement.getElementsByTagName(Names.PACKAGE).item(0);
        Package pack = getPackage(packNode);
        manufacturer.setPack(pack);

        Node dosageNode = manufElement.getElementsByTagName(Names.DOSAGE).item(0);
        manufacturer.setDosage(Integer.parseInt(dosageNode.getTextContent()));
        Element dosageElement = (Element) dosageNode;
        String dosageUnit = dosageElement.getAttribute(Names.UNIT);
        manufacturer.setDosgeUnit(dosageUnit);

        return manufacturer;
    }

    private static Package getPackage(Node packNode) {
        Package pack = new Package();
        Element packElement = (Element) packNode;

        Node packTypeNode = packElement.getElementsByTagName(Names.PACK_TYPE).item(0);
        pack.setType(packTypeNode.getTextContent());

        Node packCountNode = packElement.getElementsByTagName(Names.COUNT).item(0);
        pack.setCount(Integer.parseInt(packCountNode.getTextContent()));

        Node packPriceNode = packElement.getElementsByTagName(Names.PRICE).item(0);
        pack.setPrice(Double.parseDouble(packPriceNode.getTextContent()));
        Element packPriceElement = (Element) packPriceNode;
        String priceCurrency = packPriceElement.getAttribute(Names.CURRENCY);
        pack.setCurrency(priceCurrency);

        return pack;
    }

    private static Certificate getCertificate(Node certNode) {
        Certificate certificate = new Certificate();
        Element certElement = (Element) certNode;
        Node certNumberNode = certElement.getElementsByTagName(Names.NUMBER).item(0);
        certificate.setNumber(Integer.parseInt(certNumberNode.getTextContent()));
        Node certExpDateNode = certElement.getElementsByTagName(Names.EXPIREDAY).item(0);
        certificate.setExpDate(certExpDateNode.getTextContent());
        Node certOrgNode = certElement.getElementsByTagName(Names.ORGANIZATION).item(0);
        certificate.setOrganization(certOrgNode.getTextContent());
        return certificate;
    }

    private static String getAnalog(Node analogNode) {
        Element aElement = (Element) analogNode;
        return aElement.getTextContent();
    }

    public static Document getDocument(Medicines medicines) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        Element mElement = document.createElement(Names.MEDICINES);

        document.appendChild(mElement);

        for (Medicine medicine : medicines.getMedicines()) {
            Element medElement = document.createElement(Names.MEDICINE);
            mElement.appendChild(medElement);

            Element nameElement = document.createElement(Names.NAME);
            nameElement.setTextContent(medicine.getName());
            medElement.appendChild(nameElement);

            Element pharmElement = document.createElement(Names.PHARM);
            pharmElement.setTextContent(medicine.getPharm());
            medElement.appendChild(pharmElement);

            Element groupElement = document.createElement(Names.GROUP);
            groupElement.setTextContent(medicine.getGroup());
            medElement.appendChild(groupElement);

            for (String analog : medicine.getAnalogs()) {
                Element analogElement = document.createElement(Names.ANALOG);
                analogElement.setTextContent(analog);
                medElement.appendChild(analogElement);
            }

            for (Version version : medicine.getVersions()) {
                Element verElement = document.createElement(Names.VERSION);
                medElement.appendChild(verElement);

                Element verTypeElement = document.createElement(Names.VER_TYPE);
                verTypeElement.setTextContent(version.getType());
                verElement.appendChild(verTypeElement);

                for (Manufacturer manufacturer : version.getManufacturers()) {
                    Element manufElement = document.createElement(Names.MANUFACTURER);
                    verElement.appendChild(manufElement);

                    Certificate certificate = manufacturer.getCertificate();
                    Element certElement = document.createElement(Names.CERTIFICATE);
                    manufElement.appendChild(certElement);

                    Element certNumberElement = document.createElement(Names.NUMBER);
                    certNumberElement.setTextContent(String.valueOf(certificate.getNumber()));
                    certElement.appendChild(certNumberElement);

                    Element certExpDateElement = document.createElement(Names.EXPIREDAY);
                    certExpDateElement.setTextContent(certificate.getExpDate());
                    certElement.appendChild(certExpDateElement);

                    Element certOrgElement = document.createElement(Names.ORGANIZATION);
                    certOrgElement.setTextContent(certificate.getOrganization());
                    certElement.appendChild(certOrgElement);

                    Package pack = manufacturer.getPack();
                    Element packElement = document.createElement(Names.PACKAGE);
                    manufElement.appendChild(packElement);

                    Element packTypeElement = document.createElement(Names.PACK_TYPE);
                    packTypeElement.setTextContent(pack.getType());
                    packElement.appendChild(packTypeElement);

                    Element packCountElement = document.createElement(Names.COUNT);
                    packCountElement.setTextContent(String.valueOf(pack.getCount()));
                    packElement.appendChild(packCountElement);

                    Element packPriceElement = document.createElement(Names.PRICE);
                    packPriceElement.setTextContent(String.valueOf(pack.getPrice()));
                    packPriceElement.setAttribute(Names.CURRENCY, pack.getCurrency());
                    packElement.appendChild(packPriceElement);

                    Element dosageElement = document.createElement(Names.DOSAGE);
                    dosageElement.setTextContent(String.valueOf(manufacturer.getDosage()));
                    dosageElement.setAttribute(Names.UNIT, manufacturer.getDosgeUnit());
                    manufElement.appendChild(dosageElement);
                }
            }
        }
        return document;
    }

    public static void saveToXML(Medicines medicines, String xmlFileName) throws ParserConfigurationException, TransformerException {
        saveToXML(getDocument(medicines), xmlFileName);
    }

    public static void saveToXML(Document document, String xmlFileName) throws TransformerException {
        StreamResult result = new  StreamResult(new File(xmlFileName));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        t.transform(new DOMSource(document), result);
    }

    public static void main(String[] args) throws Exception {
        DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);

        try {
            domContr.parse(true);
        } catch (SAXException ex) {
            System.err.println("====================================");
            System.err.println("XML not valid");
            System.err.println("Medicines object --> " + domContr.getMedicines());
            System.err.println("====================================");
        }

        domContr.parse(false);

        System.out.println("====================================");
        System.out.print("Here is the medicines: \n" + domContr.getMedicines());
        System.out.println("====================================");

        Medicines medicines = domContr.getMedicines();
        DOMController.saveToXML(medicines, Constants.INVALID_XML_FILE + ".dom-result.xml");
    }
}
