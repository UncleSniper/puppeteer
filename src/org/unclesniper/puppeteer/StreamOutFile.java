package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;

public class StreamOutFile implements OutFile {

	private final OutputStream stream;

	private final TempArea tempArea;

	private File file;

	public StreamOutFile(OutputStream stream, TempArea tempArea) {
		this.stream = stream;
		this.tempArea = tempArea;
	}

	@Override
	public File asFile() throws PuppetException {
		if(file == null)
			file = (tempArea == null ? DefaultTempArea.instance : tempArea).newTempFile();
		return file;
	}

	@Override
	public OutputStream asStream() {
		return stream;
	}

	@Override
	public void close() throws CannotForwardOutFileException {
		if(file == null)
			return;
		try(FileInputStream fis = new FileInputStream(file)) {
			byte[] buffer = new byte[128];
			for(;;) {
				int count = fis.read(buffer);
				if(count <= 0)
					break;
				stream.write(buffer, 0, count);
			}
		}
		catch(IOException ioe) {
			throw new CannotForwardOutFileException(file, ioe);
		}
		finally {
			file.delete();
			file = null;
		}
	}

}
