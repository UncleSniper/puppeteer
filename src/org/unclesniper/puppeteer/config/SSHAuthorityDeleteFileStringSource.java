package org.unclesniper.puppeteer.config;

import org.unclesniper.puppeteer.FileSlave;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.AbstractDeleteFileStringSource;

@ShorthandName("sshAuthorityDeleteFileString")
public class SSHAuthorityDeleteFileStringSource extends AbstractDeleteFileStringSource {

	private SSHConfig config;

	public SSHAuthorityDeleteFileStringSource() {}

	public SSHAuthorityDeleteFileStringSource(SSHConfig config) {
		this.config = config;
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildStringImpl(FileSlave.DeleteFileInfo info, StringBuilder sink) throws PuppetException {
		sink.append(AbstractSSHConfig.buildAuthority(config, info.machine, info.execHost));
	}

}
