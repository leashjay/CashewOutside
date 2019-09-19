package seng202.team3.parsing;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import java.io.PrintStream;

/**
 * A custom error handler to report errors and warnings.  We could
 * just use the default implementation provided in DefaultHandler but
 * this is how we would arrange to do any fancy logging, recovery,
 * notification etc.  We implement org.xml.sax.ErrorHandler interface.
 */

public class MyErrorHandler {
	/**
	 * We might want to get clever about where the error output should
	 * go.  Maybe it should pop up a dialog box, go down a pipe, be
	 * written to a log etc.
	 */
	private PrintStream errDest;


	MyErrorHandler() {
		this.errDest = System.err;
	}

	public void marHandleJAXBException(Marshaller marshaller) throws Exception {
		marshaller.setEventHandler(new ValidationEventHandler() {
			@Override
			public boolean handleEvent(ValidationEvent ve) {
				errDest.println("Marshaller event handler says: " + ve.getMessage() + " (Exception: " + ve.getLinkedException() + ")");
				return false;
			}
		});
	}

	public void unmarHandleJAXBException(Unmarshaller unmarshaller) throws Exception {
		unmarshaller.setEventHandler(new ValidationEventHandler() {
			@Override
			public boolean handleEvent(ValidationEvent ve) {
				errDest.println("Unmarshaller event handler says: " + ve.getMessage() + " (Exception: " + ve.getLinkedException() + ")");
				return false;
			}
		});
	}


//	/**
//	 * Format some of the available information about or current problem.
//	 */
//	private String getParseExceptionInfo(SAXParseException spe) {
//		/*
//		 * The system ID will generally be a URL
//		 */
//		String systemId = spe.getSystemId();
//		if (systemId == null) {
//			systemId = "null";
//		}
//		String parseExceptionInfo = spe.getMessage() + "\nat line "
//				+ spe.getLineNumber() + " in " + systemId;
//		return parseExceptionInfo;
//	}
//
//	/* The warning, fatalError and error methods are from the SAX
//	 * org.xml.sax.ErrorHandler interface.  We might want to take
//	 * specific action (e.g. exit) or pass the exception on for
//	 * someone further up the line to handle.
//	 */
//
//	public void warning(SAXParseException spe) {
//		errDest.println("Warning: " + getParseExceptionInfo(spe));
//	}
//
//	public void error(SAXParseException spe) throws SAXException {
//		String errorMessage = "Error: " + getParseExceptionInfo(spe);
//		throw new SAXException(errorMessage);
//	}
//
//	public void fatalError(SAXParseException spe) throws SAXException {
//		String fatalErrorMessage = "Fatal Error: "
//				+ getParseExceptionInfo(spe);
//		throw new SAXException(fatalErrorMessage);
//	}
}
