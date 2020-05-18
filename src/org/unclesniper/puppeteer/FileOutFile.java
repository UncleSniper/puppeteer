package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class FileOutFile implements OutFile {

	private final File file;

	private OutputStream stream;

	public FileOutFile(File file) {
		this.file = file;
	}

	@Override
	public File asFile() throws PuppetException {
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
	public void close() throws IOException {
		if(stream == null)
			return;
		stream.close();
		stream = null;
	}

}
