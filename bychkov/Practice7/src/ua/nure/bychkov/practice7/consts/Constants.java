package ua.nure.bychkov.practice7.consts;

/**
 * Holds application constants.
 *
 * @author Bychkov Sergey.
 */
public final class Constants {
    private Constants() {
    }

    public static final String VALID_XML_FILE = "input.xml";
    public static final String INVALID_XML_FILE = "input-invalid.xml";
    public static final String XSD_FILE = "input.xsd";

    public static final String FEATURE_TURN_VALIDATION_ON = "http://xml.org/sax/features/validation";
    public static final String FEATURE_TURN_SCHEMA_VALIDATION_ON = "http://apache.org/xml/features/validation/schema";
}
