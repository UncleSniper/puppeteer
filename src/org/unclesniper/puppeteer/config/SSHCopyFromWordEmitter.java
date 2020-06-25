package org.unclesniper.puppeteer.config;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.CopySlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractCopyFromWordEmitter;

@ShorthandName("sshCopyFromWord")
public class SSHCopyFromWordEmitter extends AbstractCopyFromWordEmitter {

	private SSHConfig config;

	public SSHCopyFromWordEmitter() {}

	public SSHCopyFromWordEmitter(SSHConfig config) {
		this.config = config;
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildArgvImpl(CopySlave.CopyFromInfo info, Consumer<String> sink) throws PuppetException {
		AbstractSSHConfig.buildSSHCommand(config, info.machine, info.execHost, sink);
	}

}
