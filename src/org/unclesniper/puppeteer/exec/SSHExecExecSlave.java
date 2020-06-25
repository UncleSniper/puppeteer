package org.unclesniper.puppeteer.exec;

import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.WordQuoter;
import org.unclesniper.puppeteer.BashWordQuoter;
import org.unclesniper.puppeteer.StringTransform;
import org.unclesniper.puppeteer.config.SSHConfig;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.EnvExecStringSource;
import org.unclesniper.puppeteer.ArgvExecStringSource;
import org.unclesniper.puppeteer.QuotingStringTransform;
import org.unclesniper.puppeteer.CompoundExecWordEmitter;
import org.unclesniper.puppeteer.WorkDirExecStringSource;
import org.unclesniper.puppeteer.config.SSHExecWordEmitter;

@ShorthandName("execBySSH")
public class SSHExecExecSlave extends PrefabExecExecSlave {

	private SSHConfig config;

	private StringTransform shellQuoter;

	public SSHExecExecSlave() {}

	public SSHExecExecSlave(Machine execHost) {
		super(execHost);
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

	@Override
	protected void buildWords() {
		SSHExecWordEmitter cmd = new SSHExecWordEmitter(config);
		cmd.ingestObjectDefinitionLocation(this);
		addWord(cmd);
		CompoundExecWordEmitter args = new CompoundExecWordEmitter();
		args.ingestObjectDefinitionLocation(this);
		addWord(args);
		WorkDirExecStringSource workdir = new WorkDirExecStringSource();
		workdir.ingestObjectDefinitionLocation(this);
		workdir.setPrefix("cd ");
		if(shellQuoter != null)
			workdir.setTransform(shellQuoter);
		else
			workdir.setTransform(BashWordQuoter.instance);
		workdir.setSuffix("&& ");
		args.addPiece(workdir);
		EnvExecStringSource env = new EnvExecStringSource();
		env.ingestObjectDefinitionLocation(this);
		env.setMapPrefix("export");
		env.setPairPrefix(" ");
		if(shellQuoter != null)
			env.setKeyTransform(shellQuoter);
		else
			env.setKeyTransform(BashWordQuoter.instance);
		env.setPairInfix("=");
		if(shellQuoter != null)
			env.setValueTransform(shellQuoter);
		else
			env.setValueTransform(BashWordQuoter.instance);
		env.setMapSuffix(" && ");
		args.addPiece(env);
		ArgvExecStringSource argv = new ArgvExecStringSource();
		argv.ingestObjectDefinitionLocation(this);
		if(shellQuoter != null)
			argv.setTransform(shellQuoter);
		else
			argv.setTransform(BashWordQuoter.instance);
		args.addPiece(argv);
	}

}
