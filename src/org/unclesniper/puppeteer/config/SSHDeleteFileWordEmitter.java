package org.unclesniper.puppeteer.config;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.FileSlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractDeleteFileWordEmitter;

@ShorthandName("sshDeleteFileWord")
public class SSHDeleteFileWordEmitter extends AbstractDeleteFileWordEmitter {

	private SSHConfig config;

	public SSHDeleteFileWordEmitter() {}

	public SSHDeleteFileWordEmitter(SSHConfig config) {
		this.config = config;
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildArgvImpl(FileSlave.DeleteFileInfo info, Consumer<String> sink) throws PuppetException {
		AbstractSSHConfig.buildSSHCommand(config, info.machine, info.execHost, sink);
	}

}
