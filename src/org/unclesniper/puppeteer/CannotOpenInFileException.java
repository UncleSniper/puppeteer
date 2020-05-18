package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;

public class CannotOpenInFileException extends IOPuppetException {

	private final File inFile;

	public CannotOpenInFileException(File inFile, IOException cause) {
		super("Failed to open input file '" + inFile.getPath() + '\'', cause);
		this.inFile = inFile;
	}

	public File getInFile() {
		return inFile;
	}

}
