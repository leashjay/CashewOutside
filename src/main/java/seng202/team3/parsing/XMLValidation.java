package seng202.team3.parsing;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLValidation {

    public static void validateXMLFile(String fileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setValidating(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException spe) throws SAXException {
                throw spe;
            }

            @Override
            public void error(SAXParseException spe) throws SAXException {
                throw spe;
            }

            @Override
            public void fatalError(SAXParseException spe) throws SAXException {
                throw spe;
            }
        });
        builder.parse(fileName);
    }
}
