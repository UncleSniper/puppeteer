package org.unclesniper.puppeteer.config;

import java.util.Map;
import java.util.Collections;
import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.AbstractTraceable;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("emptySSHConfig")
public class EmptySSHConfig extends AbstractTraceable implements SSHConfig {

	public static final SSHConfig instance = new EmptySSHConfig();

	public EmptySSHConfig() {}

	@Override
	public IPVersion getInternetProtocolVersion(Machine machine, Machine execHost) throws PuppetException {
		return null;
	}

	@Override
	public Boolean isCompress(Machine machine, Machine execHost) throws PuppetException {
		return null;
	}

	@Override
	public Iterable<String> getCiphers(Machine machine, Machine execHost) throws PuppetException {
		return Collections.emptyList();
	}

	@Override
	public String getConfigFile(Machine machine, Machine execHost) throws PuppetException {
		return null;
	}

	@Override
	public String getIdentityFile(Machine machine, Machine execHost) throws PuppetException {
		return null;
	}

	@Override
	public Iterable<Map.Entry<String, String>> getOptions(Machine machine, Machine execHost) throws PuppetException {
		return Collections.emptyList();
	}

	@Override
	public int getPort(Machine machine, Machine execHost) throws PuppetException {
		return 0;
	}

	@Override
	public String getRemoteUser(Machine machine, Machine execHost) throws PuppetException {
		return null;
	}

	@Override
	public String getRemoteHost(Machine machine, Machine execHost) throws PuppetException {
		return null;
	}

	@Override
	public String getExecHostSSHExecutable(Machine machine, Machine execHost) throws PuppetException {
		return null;
	}

	@Override
	public String getExecHostSCPExecutable(Machine machine, Machine execHost) throws PuppetException {
		return null;
	}

}
