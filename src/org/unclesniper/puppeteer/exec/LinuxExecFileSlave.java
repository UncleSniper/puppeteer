package org.unclesniper.puppeteer.exec;

import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.util.MachineProperties;
import org.unclesniper.puppeteer.PathDeleteFileWordEmitter;
import org.unclesniper.puppeteer.StringDeleteFileWordEmitter;
import org.unclesniper.puppeteer.MachineStringPropertyDeleteFileWordEmitter;
import org.unclesniper.puppeteer.MachineStringPropertyNewTempFileWordEmitter;

@ShorthandName("linuxFileSlave")
public class LinuxExecFileSlave extends PrefabExecFileSlave {

	public LinuxExecFileSlave() {}

	public LinuxExecFileSlave(Machine execHost) {
		super(execHost);
	}

	@Override
	protected void buildTempFileWords() {
		MachineStringPropertyNewTempFileWordEmitter cmd = new MachineStringPropertyNewTempFileWordEmitter();
		cmd.ingestObjectDefinitionLocation(this);
		cmd.setProperty(MachineProperties.MKTEMP_EXECUTABLE);
		cmd.setFallback("mktemp");
		addTempFileWord(cmd);
		MachineStringPropertyNewTempFileWordEmitter p = new MachineStringPropertyNewTempFileWordEmitter();
		p.ingestObjectDefinitionLocation(this);
		p.setProperty(MachineProperties.TEMP_DIRECTORY);
		p.addPrefixWord("-p");
		addTempFileWord(p);
	}

	@Override
	protected void buildDeleteFileWords() {
		MachineStringPropertyDeleteFileWordEmitter cmd = new MachineStringPropertyDeleteFileWordEmitter();
		cmd.ingestObjectDefinitionLocation(this);
		cmd.setProperty(MachineProperties.RM_EXECUTABLE);
		cmd.setFallback("rm");
		addDeleteFileWord(cmd);
		StringDeleteFileWordEmitter dash = new StringDeleteFileWordEmitter();
		dash.ingestObjectDefinitionLocation(this);
		dash.addWord("--");
		addDeleteFileWord(dash);
		PathDeleteFileWordEmitter path = new PathDeleteFileWordEmitter();
		path.ingestObjectDefinitionLocation(this);
		addDeleteFileWord(path);
	}

}
