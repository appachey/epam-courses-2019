package ua.nure.bychkov.practice7;

import ua.nure.bychkov.practice7.controller.DOMController;
import ua.nure.bychkov.practice7.controller.SAXController;
import ua.nure.bychkov.practice7.controller.STAXController;
import ua.nure.bychkov.practice7.entity.Medicines;
import ua.nure.bychkov.practice7.util.Sorter;

import java.util.Locale;

/**
 * Entry point to XML parsers application (practice 7).
 *
 * @author Bychkov Sergey
 */
public class Main {
    public static void usage() {
        System.out.println("java ua.nure.bychkov.practice7 xmlFileName");
    }

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        if (args.length != 1) {
            usage();
            return;
        }
        String xmlFileName = args[0];
        System.out.println("Input ==> " + xmlFileName);

        ////////////////////////////////////////////////////////
        // DOM parser
        ////////////////////////////////////////////////////////

        DOMController domContr = new DOMController(xmlFileName);
        domContr.parse(true);
        Medicines medicines = domContr.getMedicines();

        Sorter.sortMedicinesByMedicineName(medicines);

        String outputXmlFile = "output.dom.xml";
        DOMController.saveToXML(medicines, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);

        ////////////////////////////////////////////////////////
        // SAX parser
        ////////////////////////////////////////////////////////

        SAXController saxContr = new SAXController(xmlFileName);
        saxContr.parse(true);
        medicines = saxContr.getMedicines();

        Sorter.sortVersionsByVersionType(medicines);

        outputXmlFile = "output.sax.xml";
        DOMController.saveToXML(medicines, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);

        ////////////////////////////////////////////////////////
        // StAX parser
        ////////////////////////////////////////////////////////

        STAXController staxController = new STAXController(xmlFileName);
        staxController.parse();
        medicines = staxController.getMedicines();

        Sorter.sortManufacturersByPackagePrice(medicines);

        outputXmlFile = "output.stax.xml";
        DOMController.saveToXML(medicines, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);
    }
}
