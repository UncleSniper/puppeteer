package org.unclesniper.puppeteer;

import java.io.InputStream;

public class IntermediateInFile implements InFile {

	private final InFile localInFile;

	private final String remoteFile;

	public IntermediateInFile(InFile localInFile, String remoteFile) {
		this.localInFile = localInFile;
		this.remoteFile = remoteFile;
	}

	@Override
	public String asFile() {
		return remoteFile;
	}

	@Override
	public InputStream asStream() throws PuppetException {
		return localInFile.asStream();
	}

	@Override
	public void copyTo(Machine machine, String destination) throws PuppetException {
		machine.getCopySlave().copyTo(machine, remoteFile, destination);
	}

	@Override
	public void close() {}

}
