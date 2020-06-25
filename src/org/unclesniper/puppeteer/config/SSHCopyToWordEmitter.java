package org.unclesniper.puppeteer.config;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.CopySlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractCopyToWordEmitter;

@ShorthandName("sshCopyToWord")
public class SSHCopyToWordEmitter extends AbstractCopyToWordEmitter {

	private SSHConfig config;

	public SSHCopyToWordEmitter() {}

	public SSHCopyToWordEmitter(SSHConfig config) {
		this.config = config;
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildArgvImpl(CopySlave.CopyToInfo info, Consumer<String> sink) throws PuppetException {
		AbstractSSHConfig.buildSSHCommand(config, info.machine, info.execHost, sink);
	}

}
