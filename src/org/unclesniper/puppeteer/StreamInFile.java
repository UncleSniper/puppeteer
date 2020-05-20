package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StreamInFile implements InFile {

	private final InputStream stream;

	private final FileSlave fileSlave;

	private final Machine fileMachine;

	private String file;

	public StreamInFile(InputStream stream, FileSlave fileSlave, Machine fileMachine) {
		this.stream = stream;
		this.fileSlave = fileSlave;
		this.fileMachine = fileMachine;
	}

	private void slurp() throws PuppetException {
		if(file != null)
			return;
		file = (fileSlave == null ? LocalFileSlave.instance : fileSlave).newTempFile(fileMachine);
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
				new File(file).delete();
				file = null;
			}
		}
	}

	@Override
	public String asFile() throws PuppetException {
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
	public void copyTo(Machine machine, String destination) throws PuppetException {
		slurp();
		machine.getCopySlave().copyTo(machine, file, destination);
	}

	@Override
	public void close() {
		if(file != null) {
			new File(file).delete();
			file = null;
		}
	}

}
