package org.unclesniper.puppeteer.config;

import java.util.Map;
import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.AbstractTraceable;

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

}
