package org.unclesniper.puppeteer;

import java.io.IOException;

public class CannotForwardOutFileException extends IOPuppetException {

	private final String tempFile;

	public CannotForwardOutFileException(String tempFile, IOException cause) {
		super("Failed to forward output file from temporary location '" + tempFile + '\'', cause);
		this.tempFile = tempFile;
	}

	public String getTempFile() {
		return tempFile;
	}

}
