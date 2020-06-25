package org.unclesniper.puppeteer.config;

import org.unclesniper.puppeteer.CopySlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractCopyToStringSource;

@ShorthandName("sshAuthorityCopyToString")
public class SSHAuthorityCopyToStringSource extends AbstractCopyToStringSource {

	private SSHConfig config;

	public SSHAuthorityCopyToStringSource() {}

	public SSHAuthorityCopyToStringSource(SSHConfig config) {
		this.config = config;
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildStringImpl(CopySlave.CopyToInfo info, StringBuilder sink) throws PuppetException {
		sink.append(AbstractSSHConfig.buildAuthority(config, info.machine, info.execHost));
	}

}
