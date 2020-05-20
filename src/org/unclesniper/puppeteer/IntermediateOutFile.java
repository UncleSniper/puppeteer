package org.unclesniper.puppeteer;

import java.io.OutputStream;

public class IntermediateOutFile implements OutFile {

	private final String file;

	public IntermediateOutFile(String file) {
		this.file = file;
	}

	@Override
	public String asFile() {
		return file;
	}

	@Override
	public OutputStream asStream() throws CannotStreamToRemoteFileException {
		throw new CannotStreamToRemoteFileException();
	}

	@Override
	public void copyFrom(Machine machine, String source) throws PuppetException {
		machine.getCopySlave().copyFrom(machine, source, file);
	}

	@Override
	public void close() {}

}
