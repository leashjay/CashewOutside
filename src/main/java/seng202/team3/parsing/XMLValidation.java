package seng202.team3.parsing;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLValidation {

    private static String getParseExceptionInfo(SAXParseException spe) {
        /*
         * The system ID will generally be a URL
         */
        String systemId = spe.getSystemId();
        if (systemId == null) {
            systemId = "null";
        }
        String parseExceptionInfo = spe.getMessage() + "\nat line "
                + spe.getLineNumber() + " in " + systemId;
        return parseExceptionInfo;
    }

    public static void validateXmlFile(String fileName) throws ParserConfigurationException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setValidating(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException spe) {
                System.err.println("Warning: " + getParseExceptionInfo(spe));
            }

            @Override
            public void error(SAXParseException spe) throws SAXException {
                String errorMessage = "Error: " + getParseExceptionInfo(spe);
                throw new SAXException(errorMessage);
            }

            @Override
            public void fatalError(SAXParseException spe) throws SAXException {
                String fatalErrorMessage = "Fatal Error: "
                        + getParseExceptionInfo(spe);
                throw new SAXException(fatalErrorMessage);
            }
        });
    }
}
