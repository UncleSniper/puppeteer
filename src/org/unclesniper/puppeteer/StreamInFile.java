package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StreamInFile implements InFile {

	private final InputStream stream;

	private final TempArea tempArea;

	private File file;

	public StreamInFile(InputStream stream, TempArea tempArea) {
		this.stream = stream;
		this.tempArea = tempArea;
	}

	private void slurp() throws PuppetException {
		if(file != null)
			return;
		file = (tempArea == null ? DefaultTempArea.instance : tempArea).newTempFile();
		boolean good = false;
		try(FileOutputStream fos = new FileOutputStream(file)) {
			byte[] buffer = new byte[128];
			for(;;) {
				int count = stream.read(buffer);
				if(count <= 0)
					break;
				fos.write(buffer, 0, count);
			}
			good = true;
		}
		catch(IOException ioe) {
			throw new CannotBufferInFileException(ioe);
		}
		finally {
			if(!good) {
				file.delete();
				file = null;
			}
		}
	}

	@Override
	public File asFile() throws PuppetException {
		slurp();
		return file;
	}

	@Override
	public InputStream asStream() throws PuppetException {
		slurp();
		try {
			return new FileInputStream(file);
		}
		catch(IOException ioe) {
			throw new CannotOpenInFileException(file, ioe);
		}
	}

	@Override
	public void close() {
		if(file != null) {
			file.delete();
			file = null;
		}
	}

}
