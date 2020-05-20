package org.unclesniper.puppeteer;

import java.io.IOException;

public class FailedToDeleteFileIOPuppetException extends IOPuppetException {

	private final String offendingFile;

	public FailedToDeleteFileIOPuppetException(String offendingFile, IOException cause) {
		super("Failed to delete file '" + offendingFile + '\'', cause);
		this.offendingFile = offendingFile;
	}

	public String getOffendingFile() {
		return offendingFile;
	}

}
