package org.unclesniper.puppeteer.config;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Collections;
import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("sshConfig")
public class StaticSSHConfig extends AbstractSSHConfig {

	private IPVersion internetProtocolVersion;

	private Boolean compress;

	private final List<String> ciphers = new LinkedList<String>();

	private String configFile;

	private String identityFile;

	private final Map<String, String> options = new HashMap<String, String>();

	private int port;

	private String remoteUser;

	private String remoteHost;

	private String execHostSSHExecutable;

	private String execHostSCPExecutable;

	public StaticSSHConfig() {}

	public IPVersion getInternetProtocolVersion() {
		return internetProtocolVersion;
	}

	public void setInternetProtocolVersion(IPVersion internetProtocolVersion) {
		this.internetProtocolVersion = internetProtocolVersion;
	}

	public Boolean isCompress() {
		return compress;
	}

	public void setCompress(Boolean compress) {
		this.compress = compress;
	}

	public Iterable<String> getCiphers() {
		return ciphers;
	}

	public void addCipher(String cipher) {
		if(cipher != null)
			ciphers.add(cipher);
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public String getIdentityFile() {
		return identityFile;
	}

	public void setIdentityFile(String identityFile) {
		this.identityFile = identityFile;
	}

	public Iterable<Map.Entry<String, String>> getOptions() {
		return options.entrySet();
	}

	public Iterable<String> getOptionKeys() {
		return options.keySet();
	}

	public String getOption(String key) {
		return options.get(key);
	}

	public void setOption(String key, String value) {
		if(key == null)
			return;
		if(value == null)
			options.remove(key);
		else
			options.put(key, value);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getExecHostSSHExecutable() {
		return execHostSSHExecutable;
	}

	public void setExecHostSSHExecutable(String execHostSSHExecutable) {
		this.execHostSSHExecutable = execHostSSHExecutable;
	}

	public String getExecHostSCPExecutable() {
		return execHostSCPExecutable;
	}

	public void setExecHostSCPExecutable(String execHostSCPExecutable) {
		this.execHostSCPExecutable = execHostSCPExecutable;
	}

	@Override
	protected IPVersion getInternetProtocolVersionImpl(Machine machine, Machine execHost) throws PuppetException {
		if(internetProtocolVersion != null)
			return internetProtocolVersion;
		if(defaults == null)
			return null;
		return defaults.getInternetProtocolVersion(machine, execHost);
	}

	@Override
	protected Boolean isCompressImpl(Machine machine, Machine execHost) throws PuppetException {
		if(compress != null)
			return compress;
		if(defaults == null)
			return null;
		return defaults.isCompress(machine, execHost);
	}

	@Override
	protected Iterable<String> getCiphersImpl(Machine machine, Machine execHost) throws PuppetException {
		if(defaults == null)
			return ciphers;
		return ciphers.isEmpty() ? defaults.getCiphers(machine, execHost) : ciphers;
	}

	@Override
	protected String getConfigFileImpl(Machine machine, Machine execHost) throws PuppetException {
		if(configFile != null)
			return configFile;
		if(defaults == null)
			return null;
		return defaults.getConfigFile(machine, execHost);
	}

	@Override
	protected String getIdentityFileImpl(Machine machine, Machine execHost) throws PuppetException {
		if(identityFile != null)
			return identityFile;
		if(defaults == null)
			return null;
		return defaults.getIdentityFile(machine, execHost);
	}

	@Override
	protected Iterable<Map.Entry<String, String>> getOptionsImpl(Machine machine, Machine execHost)
			throws PuppetException {
		if(defaults == null)
			return options.entrySet();
		Iterable<Map.Entry<String, String>> other = defaults.getOptions(machine, execHost);
		if(other == null)
			other = Collections.emptyList();
		if(options.isEmpty())
			return other;
		Map<String, String> merged = new HashMap<String, String>();
		for(Map.Entry<String, String> entry : other)
			merged.put(entry.getKey(), entry.getValue());
		merged.putAll(options);
		return merged.entrySet();
	}

	@Override
	protected int getPortImpl(Machine machine, Machine execHost) throws PuppetException {
		if(port > 0)
			return port;
		if(defaults == null)
			return 0;
		return defaults.getPort(machine, execHost);
	}

	@Override
	protected String getRemoteUserImpl(Machine machine, Machine execHost) throws PuppetException {
		if(remoteUser != null)
			return remoteUser;
		if(defaults == null)
			return null;
		return defaults.getRemoteUser(machine, execHost);
	}

	@Override
	protected String getRemoteHostImpl(Machine machine, Machine execHost) throws PuppetException {
		if(remoteHost != null)
			return remoteHost;
		if(defaults == null)
			return null;
		return defaults.getRemoteHost(machine, execHost);
	}

	@Override
	protected String getExecHostSSHExecutableImpl(Machine machine, Machine execHost) throws PuppetException {
		if(execHostSSHExecutable != null)
			return execHostSSHExecutable;
		if(defaults == null)
			return null;
		return defaults.getExecHostSSHExecutable(machine, execHost);
	}

	@Override
	protected String getExecHostSCPExecutableImpl(Machine machine, Machine execHost) throws PuppetException {
		if(execHostSCPExecutable != null)
			return execHostSCPExecutable;
		if(defaults == null)
			return null;
		return defaults.getExecHostSCPExecutable(machine, execHost);
	}

}
