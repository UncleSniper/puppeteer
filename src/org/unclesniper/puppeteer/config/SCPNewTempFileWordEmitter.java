package org.unclesniper.puppeteer.config;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.FileSlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractNewTempFileWordEmitter;

@ShorthandName("scpNewTempFileWord")
public class SCPNewTempFileWordEmitter extends AbstractNewTempFileWordEmitter {

	private SSHConfig config;

	public SCPNewTempFileWordEmitter() {}

	public SCPNewTempFileWordEmitter(SSHConfig config) {
		this.config = config;
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildArgvImpl(FileSlave.NewTempFileInfo info, Consumer<String> sink) throws PuppetException {
		AbstractSSHConfig.buildSCPCommand(config, info.machine, info.execHost, sink);
	}

}
