package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;

public class LocalFileSlave implements FileSlave {

	public static final FileSlave instance = new LocalFileSlave();

	private File directory;

	public LocalFileSlave() {}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	@Override
	public String newTempFile(Machine machine) throws PuppetException {
		try {
			return File.createTempFile("puppet", null, directory).getAbsolutePath();
		}
		catch(IOException ioe) {
			throw new FailedToCreateTempFileIOPuppetException(ioe);
		}
	}

	@Override
	public void deleteFile(Machine machine, String file) throws PuppetException {
		try {
			Files.delete(FileSystems.getDefault().getPath(file));
		}
		catch(IOException ioe) {
			throw new FailedToDeleteFileIOPuppetException(file, ioe);
		}
	}

}
