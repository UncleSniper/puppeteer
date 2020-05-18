package org.unclesniper.puppeteer;

import java.io.IOException;

public class CannotBufferInFileException extends IOPuppetException {

	public CannotBufferInFileException(IOException cause) {
		super("Failed to buffer input file", cause);
	}

}
