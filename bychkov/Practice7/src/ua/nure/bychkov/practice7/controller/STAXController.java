package ua.nure.bychkov.practice7.controller;

import org.xml.sax.helpers.DefaultHandler;

import ua.nure.bychkov.practice7.consts.Constants;
import ua.nure.bychkov.practice7.consts.Names;
import ua.nure.bychkov.practice7.entity.Manufacturer;
import ua.nure.bychkov.practice7.entity.Version;
import ua.nure.bychkov.practice7.entity.Medicine;
import ua.nure.bychkov.practice7.entity.Medicines;
import ua.nure.bychkov.practice7.entity.Certificate;
import ua.nure.bychkov.practice7.entity.Package;

import javax.xml.namespace.QName;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;

import javax.xml.transform.stream.StreamSource;

/**
 * Controller for StAX parser.
 *
 * @author Bychkov Sergey.
 */
public class STAXController extends DefaultHandler {
    private String xmlFileName;

    //main container
    private Medicines medicines;

    public Medicines getMedicines() {
        return medicines;
    }

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * Parses XML document with StAX (based on event reader).
     * There is no validation during the parsing.
     * @throws XMLStreamException
     */
    public void parse() throws XMLStreamException {
        Medicine medicine = null;

        Version version = null;

        Manufacturer manufacturer = null;

        Certificate certificate = null;

        Package pack = null;

        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();

        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();
                if (Names.MEDICINES.equals(currentElement)) {
                    medicines = new Medicines();
                    continue;
                }
                if (Names.MEDICINE.equals(currentElement)) {
                    medicine = new Medicine();
                    continue;
                }
                if (Names.VERSION.equals(currentElement)) {
                    version = new Version();
                    continue;
                }
                if (Names.MANUFACTURER.equals(currentElement)) {
                    manufacturer = new Manufacturer();
                    continue;
                }
                if (Names.DOSAGE.equals(currentElement)) {
                    Attribute attribute = startElement.getAttributeByName(new QName(Names.UNIT));
                    if (attribute != null) {
                        manufacturer.setDosgeUnit(attribute.getValue());
                    }
                    continue;
                }
                if (Names.CERTIFICATE.equals(currentElement)) {
                    certificate = new Certificate();
                    continue;
                }
                if (Names.PACKAGE.equals(currentElement)) {
                    pack = new Package();
                    continue;
                }
                if (Names.PRICE.equals(currentElement)) {
                    Attribute attribute = startElement.getAttributeByName(new QName(Names.CURRENCY));
                    if (attribute != null) {
                        pack.setCurrency(attribute.getValue());
                    }
                }
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (Names.NAME.equals(currentElement)) {
                    medicine.setName(characters.getData());
                    continue;
                }
                if (Names.PHARM.equals(currentElement)) {
                    medicine.setPharm(characters.getData());
                    continue;
                }
                if (Names.GROUP.equals(currentElement)) {
                    medicine.setGroup(characters.getData());
                    continue;
                }
                if (Names.ANALOG.equals(currentElement)) {
                    medicine.setAnalog(characters.getData());
                    continue;
                }
                if (Names.VER_TYPE.equals(currentElement)) {
                    version.setType(characters.getData());
                    continue;
                }
                if (Names.DOSAGE.equals(currentElement)) {
                    manufacturer.setDosage(Integer.parseInt(characters.getData()));
                    continue;
                }
                if (Names.NUMBER.equals(currentElement)) {
                    certificate.setNumber(Integer.parseInt(characters.getData()));
                    continue;
                }
                if (Names.EXPIREDAY.equals(currentElement)) {
                    certificate.setExpDate(characters.getData());
                    continue;
                }
                if (Names.ORGANIZATION.equals(currentElement)) {
                    certificate.setOrganization(characters.getData());
                    continue;
                }
                if (Names.PACK_TYPE.equals(currentElement)) {
                    pack.setType(characters.getData());
                    continue;
                }
                if (Names.COUNT.equals(currentElement)) {
                    pack.setCount(Integer.parseInt(characters.getData()));
                    continue;
                }
                if (Names.PRICE.equals(currentElement)) {
                    pack.setPrice(characters.getData());
                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (Names.MEDICINE.equals(localName)) {
                    medicines.getMedicines().add(medicine);
                    continue;
                }
                if (Names.VERSION.equals(localName)) {
                    medicine.getVersions().add(version);
                    continue;
                }
                if (Names.MANUFACTURER.equals(localName)) {
                    version.getManufacturers().add(manufacturer);
                    continue;
                }
                if (Names.CERTIFICATE.equals(localName)) {
                    manufacturer.setCertificate(certificate);
                    continue;
                }
                if (Names.PACKAGE.equals(localName)) {
                    manufacturer.setPack(pack);
                }
            }
        }
        reader.close();
    }

    public static void main(String[] args) throws Exception {
        STAXController staxController = new STAXController(Constants.VALID_XML_FILE);
        staxController.parse();

        Medicines medicines = staxController.getMedicines();

        System.out.println("====================================");
        System.out.print("Here is the medicines: \n" + medicines);
        System.out.println("====================================");
    }
}
