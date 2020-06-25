package org.unclesniper.puppeteer.config;

import org.unclesniper.puppeteer.CopySlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractCopyFromStringSource;

@ShorthandName("sshAuthorityCopyFromString")
public class SSHAuthorityCopyFromStringSource extends AbstractCopyFromStringSource {

	private SSHConfig config;

	public SSHAuthorityCopyFromStringSource() {}

	public SSHAuthorityCopyFromStringSource(SSHConfig config) {
		this.config = config;
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildStringImpl(CopySlave.CopyFromInfo info, StringBuilder sink) throws PuppetException {
		sink.append(AbstractSSHConfig.buildAuthority(config, info.machine, info.execHost));
	}

}
