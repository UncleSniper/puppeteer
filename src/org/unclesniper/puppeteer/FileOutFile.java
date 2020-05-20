package org.unclesniper.puppeteer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class FileOutFile implements OutFile {

	private final String file;

	private OutputStream stream;

	public FileOutFile(String file) {
		this.file = file;
	}

	@Override
	public String asFile() throws PuppetException {
		return file;
	}

	@Override
	public OutputStream asStream() throws PuppetException {
		try {
			if(stream != null) {
				stream.close();
				stream = null;
			}
			stream = new FileOutputStream(file);
			return stream;
		}
		catch(IOException ioe) {
			throw new CannotOpenOutFileException(file, ioe);
		}
	}

	@Override
	public void copyFrom(Machine machine, String source) throws PuppetException {
		machine.getCopySlave().copyFrom(machine, source, file);
	}

	@Override
	public void close() throws IOException {
		if(stream == null)
			return;
		stream.close();
		stream = null;
	}

}
