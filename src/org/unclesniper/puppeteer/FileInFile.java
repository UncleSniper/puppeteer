package org.unclesniper.puppeteer;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class FileInFile implements InFile {

	private final String file;

	private InputStream stream;

	public FileInFile(String file) {
		this.file = file;
	}

	@Override
	public String asFile() {
		return file;
	}

	@Override
	public InputStream asStream() throws PuppetException {
		try {
			if(stream != null) {
				stream.close();
				stream = null;
			}
			stream = new FileInputStream(file);
			return stream;
		}
		catch(IOException ioe) {
			throw new CannotOpenInFileException(file, ioe);
		}
	}

	@Override
	public void copyTo(Machine machine, String destination) throws PuppetException {
		machine.getCopySlave().copyTo(machine, file, destination);
	}

	@Override
	public void close() throws IOException {
		if(stream == null)
			return;
		stream.close();
		stream = null;
	}

}
