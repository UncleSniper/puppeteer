package org.unclesniper.puppeteer.config;

import java.util.Map;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.Doom;
import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.AbstractTraceable;
import org.unclesniper.puppeteer.MissingTargetHostnameException;

public abstract class AbstractSSHConfig extends AbstractTraceable implements SSHConfig {

	protected SSHConfig defaults;

	public AbstractSSHConfig() {}

	public SSHConfig getDefaults() {
		return defaults;
	}

	public void setDefaults(SSHConfig defaults) {
		this.defaults = defaults;
	}

	protected abstract IPVersion getInternetProtocolVersionImpl(Machine machine, Machine execHost)
			throws PuppetException;

	protected abstract Boolean isCompressImpl(Machine machine, Machine execHost) throws PuppetException;

	protected abstract Iterable<String> getCiphersImpl(Machine machine, Machine execHost) throws PuppetException;

	protected abstract String getConfigFileImpl(Machine machine, Machine execHost) throws PuppetException;

	protected abstract String getIdentityFileImpl(Machine machine, Machine execHost) throws PuppetException;

	protected abstract Iterable<Map.Entry<String, String>> getOptionsImpl(Machine machine, Machine execHost)
			throws PuppetException;

	protected abstract int getPortImpl(Machine machine, Machine execHost) throws PuppetException;

	protected abstract String getRemoteUserImpl(Machine machine, Machine execHost) throws PuppetException;

	protected abstract String getRemoteHostImpl(Machine machine, Machine execHost) throws PuppetException;

	protected abstract String getExecHostSSHExecutableImpl(Machine machine, Machine execHost)
			throws PuppetException;

	protected abstract String getExecHostSCPExecutableImpl(Machine machine, Machine execHost)
			throws PuppetException;

	@Override
	public IPVersion getInternetProtocolVersion(Machine machine, Machine execHost) throws PuppetException {
		try {
			return getInternetProtocolVersionImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public Boolean isCompress(Machine machine, Machine execHost) throws PuppetException {
		try {
			return isCompressImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public Iterable<String> getCiphers(Machine machine, Machine execHost) throws PuppetException {
		try {
			return getCiphersImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public String getConfigFile(Machine machine, Machine execHost) throws PuppetException {
		try {
			return getConfigFileImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public String getIdentityFile(Machine machine, Machine execHost) throws PuppetException {
		try {
			return getIdentityFileImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public Iterable<Map.Entry<String, String>> getOptions(Machine machine, Machine execHost)
			throws PuppetException {
		try {
			return getOptionsImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public int getPort(Machine machine, Machine execHost) throws PuppetException {
		try {
			return getPortImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public String getRemoteUser(Machine machine, Machine execHost) throws PuppetException {
		try {
			return getRemoteUserImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public String getRemoteHost(Machine machine, Machine execHost) throws PuppetException {
		try {
			return getRemoteHostImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public String getExecHostSSHExecutable(Machine machine, Machine execHost) throws PuppetException {
		try {
			return getExecHostSSHExecutableImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public String getExecHostSCPExecutable(Machine machine, Machine execHost) throws PuppetException {
		try {
			return getExecHostSCPExecutableImpl(machine, execHost);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	public static void buildSSHCommand(SSHConfig config, Machine machine, Machine execHost, Consumer<String> sink)
			throws PuppetException {
		if(config == null)
			config = EmptySSHConfig.instance;
		String executable = config.getExecHostSSHExecutable(machine, execHost);
		sink.accept(executable == null ? "ssh" : executable);
		AbstractSSHConfig.buildCommonOptions(config, machine, execHost, sink);
		int port = config.getPort(machine, execHost);
		if(port > 0)
			sink.accept("-p" + port);
		sink.accept("--");
		sink.accept(AbstractSSHConfig.buildAuthority(config, machine, execHost));
	}

	public static void buildSCPCommand(SSHConfig config, Machine machine, Machine execHost, Consumer<String> sink)
			throws PuppetException {
		if(config == null)
			config = EmptySSHConfig.instance;
		String executable = config.getExecHostSCPExecutable(machine, execHost);
		sink.accept(executable == null ? "scp" : executable);
		AbstractSSHConfig.buildCommonOptions(config, machine, execHost, sink);
		int port = config.getPort(machine, execHost);
		if(port > 0)
			sink.accept("-P" + port);
		sink.accept("--");
	}

	private static void buildCommonOptions(SSHConfig config, Machine machine, Machine execHost,
			Consumer<String> sink) throws PuppetException {
		IPVersion ipv = config.getInternetProtocolVersion(machine, execHost);
		switch(ipv == null ? IPVersion.ANY : ipv) {
			case ANY:
				break;
			case IPV4:
				sink.accept("-4");
				break;
			case IPV6:
				sink.accept("-6");
				break;
			default:
				throw new Doom("Unrecognized IPVersion: " + ipv.name());
		}
		Boolean compress = config.isCompress(machine, execHost);
		if(compress != null && compress)
			sink.accept("-C");
		StringBuilder builder = null;
		Iterable<String> ciphers = config.getCiphers(machine, execHost);
		if(ciphers != null) {
			for(String cipher : ciphers) {
				if(cipher == null || cipher.length() == 0)
					continue;
				if(builder == null)
					builder = new StringBuilder();
				else
					builder.append(',');
				builder.append(cipher);
			}
		}
		if(builder != null) {
			sink.accept("-c");
			sink.accept(builder.toString());
		}
		String configFile = config.getConfigFile(machine, execHost);
		if(configFile != null) {
			sink.accept("-F");
			sink.accept(configFile);
		}
		String identityFile = config.getIdentityFile(machine, execHost);
		if(identityFile != null) {
			sink.accept("-i");
			sink.accept(identityFile);
		}
		Iterable<Map.Entry<String, String>> options = config.getOptions(machine, execHost);
		if(options != null) {
			for(Map.Entry<String, String> entry : options) {
				String key = entry.getKey(), value = entry.getValue();
				if(key == null || key.length() == 0 || value == null)
					continue;
				sink.accept("-o");
				sink.accept(key + '=' + value);
			}
		}
	}

	public static String buildAuthority(SSHConfig config, Machine machine, Machine execHost)
			throws PuppetException {
		if(config == null)
			config = EmptySSHConfig.instance;
		String user = config.getRemoteUser(machine, execHost);
		String host = config.getRemoteHost(machine, execHost);
		if(host == null) {
			if(machine != null)
				host = machine.getHostname();
			if(host == null)
				throw new MissingTargetHostnameException(machine);
		}
		return user == null || user.length() == 0 ? host : user + '@' + host;
	}

}
