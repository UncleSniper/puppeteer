package org.unclesniper.puppeteer.config;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.ExecSlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractExecWordEmitter;

@ShorthandName("scpExecWord")
public class SCPExecWordEmitter extends AbstractExecWordEmitter {

	private SSHConfig config;

	public SCPExecWordEmitter() {}

	public SCPExecWordEmitter(SSHConfig config) {
		this.config = config;
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildArgvImpl(ExecSlave.ExecInfo info, Consumer<String> sink) throws PuppetException {
		AbstractSSHConfig.buildSCPCommand(config, info.machine, info.execHost, sink);
	}

}
