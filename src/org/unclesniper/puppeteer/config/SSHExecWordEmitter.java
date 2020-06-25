package org.unclesniper.puppeteer.config;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.ExecSlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractExecWordEmitter;

@ShorthandName("sshExecWord")
public class SSHExecWordEmitter extends AbstractExecWordEmitter {

	private SSHConfig config;

	public SSHExecWordEmitter() {}

	public SSHExecWordEmitter(SSHConfig config) {
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
		AbstractSSHConfig.buildSSHCommand(config, info.machine, info.execHost, sink);
	}

}
