package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;

public class StreamOutFile implements OutFile {

	private final OutputStream stream;

	private final FileSlave fileSlave;

	private final Machine fileMachine;

	private String file;

	public StreamOutFile(OutputStream stream, FileSlave fileSlave, Machine fileMachine) {
		this.stream = stream;
		this.fileSlave = fileSlave;
		this.fileMachine = fileMachine;
	}

	@Override
	public String asFile() throws PuppetException {
		if(file == null)
			file = (fileSlave == null ? LocalFileSlave.instance : fileSlave).newTempFile(fileMachine);
		return file;
	}

	@Override
	public OutputStream asStream() {
		return stream;
	}

	@Override
	public void copyFrom(Machine machine, String source) throws PuppetException {
		machine.getCopySlave().copyFrom(machine, source, stream);
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
			new File(file).delete();
			file = null;
		}
	}

}
