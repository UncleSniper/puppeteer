package org.unclesniper.puppeteer.config;

import org.unclesniper.puppeteer.ExecSlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractExecStringSource;

@ShorthandName("sshAuthorityExecString")
public class SSHAuthorityExecStringSource extends AbstractExecStringSource {

	private SSHConfig config;

	public SSHAuthorityExecStringSource() {}

	public SSHAuthorityExecStringSource(SSHConfig config) {
		this.config = config;
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildStringImpl(ExecSlave.ExecInfo info, StringBuilder sink) throws PuppetException {
		sink.append(AbstractSSHConfig.buildAuthority(config, info.machine, info.execHost));
	}

}
