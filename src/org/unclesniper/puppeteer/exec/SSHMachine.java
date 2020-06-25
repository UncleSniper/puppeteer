package org.unclesniper.puppeteer.exec;

import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.FileSlave;
import org.unclesniper.puppeteer.WordQuoter;
import org.unclesniper.puppeteer.StringTransform;
import org.unclesniper.puppeteer.config.SSHConfig;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.QuotingStringTransform;

@ShorthandName("sshMachine")
public class SSHMachine extends Machine {

	private SSHConfig config;

	private StringTransform shellQuoter;

	private Machine execHost;

	private FileSlave localFileSlave;

	private boolean explicitIntermediate;

	public SSHMachine() {}

	public SSHMachine(String hostname) {
		super(hostname);
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	public StringTransform getShellQuoter() {
		return shellQuoter;
	}

	public void setShellQuoter(StringTransform shellQuoter) {
		this.shellQuoter = shellQuoter;
	}

	public void setShellQuoter(WordQuoter shellQuoter) {
		this.shellQuoter = shellQuoter == null ? null : new QuotingStringTransform(shellQuoter);
	}

	public Machine getExecHost() {
		return execHost;
	}

	public void setExecHost(Machine execHost) {
		this.execHost = execHost;
	}

	public FileSlave getLocalFileSlave() {
		return localFileSlave;
	}

	public void setLocalFileSlave(FileSlave localFileSlave) {
		this.localFileSlave = localFileSlave;
	}

	public boolean isExplicitIntermediate() {
		return explicitIntermediate;
	}

	public void setExplicitIntermediate(boolean explicitIntermediate) {
		this.explicitIntermediate = explicitIntermediate;
	}

	@Override
	protected void buildExecSlave() {
		SSHExecExecSlave slave = new SSHExecExecSlave();
		slave.ingestObjectDefinitionLocation(this);
		slave.setExecHost(execHost);
		slave.setConfig(config);
		slave.setShellQuoter(shellQuoter);
		setExecSlave(slave);
	}

	@Override
	protected void buildCopySlave() {
		SCPExecCopySlave slave = new SCPExecCopySlave();
		slave.ingestObjectDefinitionLocation(this);
		slave.setExecHost(execHost);
		slave.setLocalFileSlave(localFileSlave);
		slave.setExplicitIntermediate(explicitIntermediate);
		slave.setConfig(config);
		setCopySlave(slave);
	}

	@Override
	protected void buildFileSlave() {
		LinuxExecFileSlave slave = new LinuxExecFileSlave();
		slave.ingestObjectDefinitionLocation(this);
		slave.setExecHost(this);
		setFileSlave(slave);
	}

}
