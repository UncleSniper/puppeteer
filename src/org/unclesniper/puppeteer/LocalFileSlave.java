package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;

public class LocalFileSlave extends AbstractFileSlave {

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
	protected String newTempFileImpl(Machine machine) throws PuppetException {
		try {
			String path = File.createTempFile("puppet", null, directory).getAbsolutePath();
			if(LocalUtils.DEBUG_LOCAL) {
				System.err.println("*** DEBUG: newTempFile:");
				System.err.println("***            => '" + path + '\'');
			}
			return path;
		}
		catch(IOException ioe) {
			throw new FailedToCreateTempFileIOPuppetException(ioe);
		}
	}

	@Override
	protected void deleteFileImpl(Machine machine, String file) throws PuppetException {
		if(LocalUtils.DEBUG_LOCAL) {
			System.err.println("*** DEBUG: deleteFile:");
			System.err.println("***            path: '" + file + '\'');
		}
		try {
			Files.delete(FileSystems.getDefault().getPath(file));
		}
		catch(IOException ioe) {
			throw new FailedToDeleteFileIOPuppetException(file, ioe);
		}
	}

}
