package org.unclesniper.puppeteer;

import java.io.IOException;

public class CannotOpenOutFileException extends IOPuppetException {

	private final String outFile;

	public CannotOpenOutFileException(String outFile, IOException cause) {
		super("Failed to open output file '" + outFile + '\'', cause);
		this.outFile = outFile;
	}

	public String getOutFile() {
		return outFile;
	}

}
