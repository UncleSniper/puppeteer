package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;

public class CannotOpenOutFileException extends IOPuppetException {

	private final File outFile;

	public CannotOpenOutFileException(File outFile, IOException cause) {
		super("Failed to open output file '" + outFile.getPath() + '\'', cause);
		this.outFile = outFile;
	}

	public File getOutFile() {
		return outFile;
	}

}
