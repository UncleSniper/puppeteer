package org.unclesniper.puppeteer.config;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.CopySlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractCopyFromWordEmitter;

@ShorthandName("scpCopyFromWord")
public class SCPCopyFromWordEmitter extends AbstractCopyFromWordEmitter {

	private SSHConfig config;

	public SCPCopyFromWordEmitter() {}

	public SCPCopyFromWordEmitter(SSHConfig config) {
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
		AbstractSSHConfig.buildSCPCommand(config, info.machine, info.execHost, sink);
	}

}
