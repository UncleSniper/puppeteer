package org.unclesniper.puppeteer.config;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Collections;
import org.unclesniper.puppeteer.Doom;
import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.util.TriState;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.util.MachineProperties;
import org.unclesniper.puppeteer.util.InvalidTriStateException;

@ShorthandName("machineSSHConfig")
public class MachinePropertySSHConfig extends AbstractSSHConfig {

	private String internetProtocolVersionProperty;

	private String compressProperty;

	private String ciphersProperty;

	private String configFileProperty;

	private String identityFileProperty;

	private String portProperty;

	private String remoteUserProperty;

	private String remoteHostProperty;

	private String execHostSSHExecutableProperty;

	private String execHostSCPExecutableProperty;

	public MachinePropertySSHConfig() {}

	public String getInternetProtocolVersionProperty() {
		return internetProtocolVersionProperty;
	}

	public void setInternetProtocolVersionProperty(String internetProtocolVersionProperty) {
		this.internetProtocolVersionProperty = internetProtocolVersionProperty;
	}

	public String getCompressProperty() {
		return compressProperty;
	}

	public void setCompressProperty(String compressProperty) {
		this.compressProperty = compressProperty;
	}

	public String getCiphersProperty() {
		return ciphersProperty;
	}

	public void setCiphersProperty(String ciphersProperty) {
		this.ciphersProperty = ciphersProperty;
	}

	public String getConfigFileProperty() {
		return configFileProperty;
	}

	public void setConfigFileProperty(String configFileProperty) {
		this.configFileProperty = configFileProperty;
	}

	public String getIdentityFileProperty() {
		return identityFileProperty;
	}

	public void setIdentityFileProperty(String identityFileProperty) {
		this.identityFileProperty = identityFileProperty;
	}

	public String getPortProperty() {
		return portProperty;
	}

	public void setPortProperty(String portProperty) {
		this.portProperty = portProperty;
	}

	public String getRemoteUserProperty() {
		return remoteUserProperty;
	}

	public void setRemoteUserProperty(String remoteUserProperty) {
		this.remoteUserProperty = remoteUserProperty;
	}

	public String getRemoteHostProperty() {
		return remoteHostProperty;
	}

	public void setRemoteHostProperty(String remoteHostProperty) {
		this.remoteHostProperty = remoteHostProperty;
	}

	public String getExecHostSSHExecutableProperty() {
		return execHostSSHExecutableProperty;
	}

	public void setExecHostSSHExecutableProperty(String execHostSSHExecutableProperty) {
		this.execHostSSHExecutableProperty = execHostSSHExecutableProperty;
	}

	public String getExecHostSCPExecutableProperty() {
		return execHostSCPExecutableProperty;
	}

	public void setExecHostSCPExecutableProperty(String execHostSCPExecutableProperty) {
		this.execHostSCPExecutableProperty = execHostSCPExecutableProperty;
	}

	@Override
	protected IPVersion getInternetProtocolVersionImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = internetProtocolVersionProperty == null
				? MachineProperties.SSH_IP_VERSION : internetProtocolVersionProperty;
		String value = machine == null ? null : machine.getStringProperty(property);
		if(value != null) {
			value = value.toUpperCase();
			try {
				return IPVersion.valueOf(value);
			}
			catch(IllegalArgumentException iae) {
				throw new InvalidIPVersionException(value, "in property '" + property + "' of machine"
						+ Machine.makeMessage(machine, " ", ""), iae);
			}
		}
		if(defaults == null)
			return null;
		return defaults.getInternetProtocolVersion(machine, execHost);
	}

	@Override
	protected Boolean isCompressImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = compressProperty == null ? MachineProperties.SSH_COMPRESS : compressProperty;
		String value = machine == null ? null : machine.getStringProperty(property);
		if(value != null) {
			value = value.toUpperCase();
			TriState tri;
			try {
				tri = TriState.valueOf(value);
			}
			catch(IllegalArgumentException iae) {
				throw new InvalidTriStateException(value, "in property '" + property + "' of machine"
						+ Machine.makeMessage(machine, " " , ""), iae);
			}
			switch(tri) {
				case YES:
					return Boolean.TRUE;
				case NO:
					return Boolean.FALSE;
				case MAYBE:
					return null;
				default:
					throw new Doom("Unrecognized TriState: " + tri.name());
			}
		}
		if(defaults == null)
			return null;
		return defaults.isCompress(machine, execHost);
	}

	@Override
	protected Iterable<String> getCiphersImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = ciphersProperty == null ? MachineProperties.SSH_CIPHERS : ciphersProperty;
		String value = machine == null ? null : machine.getStringProperty(property);
		if(value != null) {
			List<String> elements = new LinkedList<String>();
			int old = 0, pos;
			while((pos = value.indexOf(',', old)) >= 0) {
				if(pos > old)
					elements.add(value.substring(old, pos));
				old = pos + 1;
			}
			if(old < value.length())
				elements.add(value.substring(old));
			return elements;
		}
		if(defaults == null)
			return null;
		return defaults.getCiphers(machine, execHost);
	}

	@Override
	protected String getConfigFileImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = configFileProperty == null ? MachineProperties.SSH_CONFIG_FILE : configFileProperty;
		String value = machine == null ? null : machine.getStringProperty(property);
		if(value != null)
			return value;
		if(defaults == null)
			return null;
		return defaults.getConfigFile(machine, execHost);
	}

	@Override
	protected String getIdentityFileImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = identityFileProperty == null ? MachineProperties.SSH_IDENTITY_FILE : identityFileProperty;
		String value = machine == null ? null : machine.getStringProperty(property);
		if(value != null)
			return value;
		if(defaults == null)
			return null;
		return defaults.getIdentityFile(machine, execHost);
	}

	@Override
	protected Iterable<Map.Entry<String, String>> getOptionsImpl(Machine machine, Machine execHost)
			throws PuppetException {
		return defaults == null ? Collections.emptyList() : defaults.getOptions(machine, execHost);
	}

	@Override
	protected int getPortImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = portProperty == null ? MachineProperties.SSH_PORT : portProperty;
		Integer value = machine == null ? null : machine.getIntProperty(property);
		if(value != null && value > 0)
			return value;
		if(defaults == null)
			return 0;
		return defaults.getPort(machine, execHost);
	}

	@Override
	protected String getRemoteUserImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = remoteUserProperty == null ? MachineProperties.SSH_REMOTE_USER : remoteUserProperty;
		String value = machine == null ? null : machine.getStringProperty(property);
		if(value != null)
			return value;
		if(defaults == null)
			return null;
		return defaults.getRemoteUser(machine, execHost);
	}

	@Override
	protected String getRemoteHostImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = remoteHostProperty == null ? MachineProperties.SSH_REMOTE_HOST : remoteHostProperty;
		String value = machine == null ? null : machine.getStringProperty(property);
		if(value != null)
			return value;
		if(defaults == null)
			return null;
		return defaults.getRemoteHost(machine, execHost);
	}

	@Override
	protected String getExecHostSSHExecutableImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = execHostSSHExecutableProperty == null
				? MachineProperties.SSH_EXECUTABLE : execHostSSHExecutableProperty;
		String value = execHost == null ? null : execHost.getStringProperty(property);
		if(value != null)
			return value;
		if(defaults == null)
			return null;
		return defaults.getExecHostSSHExecutable(machine, execHost);
	}

	@Override
	protected String getExecHostSCPExecutableImpl(Machine machine, Machine execHost) throws PuppetException {
		String property = execHostSCPExecutableProperty == null
				? MachineProperties.SCP_EXECUTABLE : execHostSCPExecutableProperty;
		String value = execHost == null ? null : execHost.getStringProperty(property);
		if(value != null)
			return value;
		if(defaults == null)
			return null;
		return defaults.getExecHostSCPExecutable(machine, execHost);
	}

}
