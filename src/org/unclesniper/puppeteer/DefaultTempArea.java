package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;

public class DefaultTempArea implements TempArea {

	public static final TempArea instance = new DefaultTempArea();

	private File directory;

	public DefaultTempArea() {}

	public DefaultTempArea(File directory) {
		this.directory = directory;
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	@Override
	public File newTempFile() throws PuppetException {
		try {
			return File.createTempFile("puppet", null, directory);
		}
		catch(IOException ioe) {
			throw new FailedToCreateTempFileIOPuppetException(ioe);
		}
	}

}
