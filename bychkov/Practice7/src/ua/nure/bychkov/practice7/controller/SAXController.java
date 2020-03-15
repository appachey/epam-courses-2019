package ua.nure.bychkov.practice7.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.bychkov.practice7.consts.Constants;
import ua.nure.bychkov.practice7.consts.Names;
import ua.nure.bychkov.practice7.entity.Medicines;
import ua.nure.bychkov.practice7.entity.Medicine;
import ua.nure.bychkov.practice7.entity.Version;
import ua.nure.bychkov.practice7.entity.Manufacturer;
import ua.nure.bychkov.practice7.entity.Certificate;
import ua.nure.bychkov.practice7.entity.Package;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXController extends DefaultHandler {
    private String xmlFileName;

    private String currentElement;

    private Medicines medicines;

    private Medicine medicine;

    private Version version;

    private Manufacturer manufacturer;

    private Certificate certificate;

    private Package pack;

    public  SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setNamespaceAware(true);

        if (validate) {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }

    public Medicines getMedicines() {
        return medicines;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = localName;

        if (Names.MEDICINES.equals(currentElement)) {
            medicines = new Medicines();
            return;
        }
        if (Names.MEDICINE.equals(currentElement)) {
            medicine = new Medicine();
            return;
        }
        if (Names.VERSION.equals(currentElement)) {
            version = new Version();
            return;
        }
        if (Names.MANUFACTURER.equals(currentElement)) {
            manufacturer = new Manufacturer();
            return;
        }
        if (Names.DOSAGE.equals(currentElement)) {
            if (attributes.getLength() > 0) {
                manufacturer.setDosgeUnit(attributes.getValue(uri, Names.UNIT));
            }
            return;
        }
        if (Names.CERTIFICATE.equals(currentElement)) {
            certificate = new Certificate();
            return;
        }
        if (Names.PACKAGE.equals(currentElement)) {
            pack = new Package();
            return;
        }
        if (Names.PRICE.equals(currentElement)) {
            if (attributes.getLength() > 0) {
                pack.setCurrency(attributes.getValue(uri, Names.CURRENCY));
            }
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (Names.MEDICINE.equals(localName)) {
            medicines.getMedicines().add(medicine);
            return;
        }
        if (Names.VERSION.equals(localName)) {
            medicine.getVersions().add(version);
            return;
        }
        if (Names.MANUFACTURER.equals(localName)) {
            version.getManufacturers().add(manufacturer);
            return;
        }
        if (Names.CERTIFICATE.equals(localName)) {
            manufacturer.setCertificate(certificate);
            return;
        }
        if (Names.PACKAGE.equals(localName)) {
            manufacturer.setPack(pack);
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String elementText = new String(ch, start, length).trim();

        if (elementText.isEmpty()) {
            return;
        }

        if (Names.NAME.equals(currentElement)) {
            medicine.setName(elementText);
            return;
        }
        if (Names.PHARM.equals(currentElement)) {
            medicine.setPharm(elementText);
            return;
        }
        if (Names.GROUP.equals(currentElement)) {
            medicine.setGroup(elementText);
            return;
        }
        if (Names.ANALOG.equals(currentElement)) {
            medicine.setAnalog(elementText);
            return;
        }
        if (Names.VER_TYPE.equals(currentElement)) {
            version.setType(elementText);
            return;
        }
        if (Names.DOSAGE.equals(currentElement)) {
            manufacturer.setDosage(Integer.parseInt(elementText));
            return;
        }
        if (Names.NUMBER.equals(currentElement)) {
            certificate.setNumber(Integer.parseInt(elementText));
            return;
        }
        if (Names.EXPIREDAY.equals(currentElement)) {
            certificate.setExpDate(elementText);
            return;
        }
        if (Names.ORGANIZATION.equals(currentElement)) {
            certificate.setOrganization(elementText);
            return;
        }
        if (Names.PACK_TYPE.equals(currentElement)) {
            pack.setType(elementText);
            return;
        }
        if (Names.COUNT.equals(currentElement)) {
            pack.setCount(Integer.parseInt(elementText));
            return;
        }
        if (Names.PRICE.equals(currentElement)) {
            pack.setPrice(Double.parseDouble(elementText));
            return;
        }
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }

    public static void main(String[] args) throws Exception {
        SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);

        saxContr.parse(true);

        Medicines medicines = saxContr.getMedicines();

        System.out.println("====================================");
        System.out.print("Here is the medicines: \n" + medicines);
        System.out.println("====================================");

        String xmlFileName = "output.sax.xml";
        DOMController.saveToXML(medicines, xmlFileName);

        saxContr = new SAXController(Constants.INVALID_XML_FILE);

        try {
            saxContr.parse(true);
        } catch (Exception e) {
            System.err.println("====================================");
            System.err.println("Validation is failed:\n" + e.getMessage());
            System.err
                    .println("Try to print medicines object:" + saxContr.getMedicines());
            System.err.println("====================================");
        }

        saxContr.parse(false);

        System.out.println("====================================");
        System.out.print("Here is the medicines: \n" + saxContr.getMedicines());
        System.out.println("====================================");

    }
}
