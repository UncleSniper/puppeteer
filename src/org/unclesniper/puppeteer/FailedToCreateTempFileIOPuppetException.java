package org.unclesniper.puppeteer;

import java.io.IOException;

public class FailedToCreateTempFileIOPuppetException extends IOPuppetException {

	public FailedToCreateTempFileIOPuppetException(IOException cause) {
		super("Failed to create temporary file", cause);
	}

}
