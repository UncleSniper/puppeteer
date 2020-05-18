package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;

public class CannotForwardOutFileException extends IOPuppetException {

	private final File tempFile;

	public CannotForwardOutFileException(File tempFile, IOException cause) {
		super("Failed to forward output file from temporary location '" + tempFile.getPath() + '\'', cause);
		this.tempFile = tempFile;
	}

	public File getTempFile() {
		return tempFile;
	}

}
