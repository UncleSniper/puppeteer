package org.unclesniper.puppeteer;

import java.io.File;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("localhost")
public class LocalMachine extends Machine {

	private File tempDirectory;

	public LocalMachine() {}

	public LocalMachine(String hostname) {
		super(hostname);
	}

	public File getTempDirectory() {
		return tempDirectory;
	}

	public void setTempDirectory(File tempDirectory) {
		this.tempDirectory = tempDirectory;
	}

	@Override
	protected void buildExecSlave() {
		LocalExecSlave slave = new LocalExecSlave();
		slave.ingestObjectDefinitionLocation(this);
		setExecSlave(slave);
	}

	@Override
	protected void buildCopySlave() {
		LocalCopySlave slave = new LocalCopySlave();
		slave.ingestObjectDefinitionLocation(this);
		setCopySlave(slave);
	}

	@Override
	protected void buildFileSlave() {
		LocalFileSlave slave = new LocalFileSlave();
		slave.ingestObjectDefinitionLocation(this);
		slave.setTempDirectory(tempDirectory);
		setFileSlave(slave);
	}

}
