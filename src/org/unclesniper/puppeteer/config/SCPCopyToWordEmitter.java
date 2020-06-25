package org.unclesniper.puppeteer.config;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.CopySlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractCopyToWordEmitter;

@ShorthandName("scpCopyToWord")
public class SCPCopyToWordEmitter extends AbstractCopyToWordEmitter {

	private SSHConfig config;

	public SCPCopyToWordEmitter() {}

	public SCPCopyToWordEmitter(SSHConfig config) {
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
		AbstractSSHConfig.buildSCPCommand(config, info.machine, info.execHost, sink);
	}

}
