package org.unclesniper.puppeteer;

import java.io.IOException;

public class CannotOpenInFileException extends IOPuppetException {

	private final String inFile;

	public CannotOpenInFileException(String inFile, IOException cause) {
		super("Failed to open input file '" + inFile + '\'', cause);
		this.inFile = inFile;
	}

	public String getInFile() {
		return inFile;
	}

}
