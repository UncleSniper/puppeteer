package org.unclesniper.puppeteer.exec;

import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.config.SSHConfig;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.CompoundCopyToWordEmitter;
import org.unclesniper.puppeteer.SourcePathCopyToWordEmitter;
import org.unclesniper.puppeteer.config.SCPCopyToWordEmitter;
import org.unclesniper.puppeteer.CompoundCopyFromWordEmitter;
import org.unclesniper.puppeteer.config.SCPCopyFromWordEmitter;
import org.unclesniper.puppeteer.SourcePathCopyFromStringSource;
import org.unclesniper.puppeteer.DestinationPathCopyToStringSource;
import org.unclesniper.puppeteer.DestinationPathCopyFromWordEmitter;
import org.unclesniper.puppeteer.config.SSHAuthorityCopyToStringSource;
import org.unclesniper.puppeteer.config.SSHAuthorityCopyFromStringSource;

@ShorthandName("copyBySCP")
public class SCPExecCopySlave extends PrefabExecCopySlave {

	private SSHConfig config;

	public SCPExecCopySlave() {}

	public SCPExecCopySlave(Machine execHost) {
		super(execHost);
	}

	public SSHConfig getConfig() {
		return config;
	}

	public void setConfig(SSHConfig config) {
		this.config = config;
	}

	@Override
	protected void buildToWords() {
		SCPCopyToWordEmitter cmd = new SCPCopyToWordEmitter(config);
		cmd.ingestObjectDefinitionLocation(this);
		addToWord(cmd);
		SourcePathCopyToWordEmitter src = new SourcePathCopyToWordEmitter();
		src.ingestObjectDefinitionLocation(this);
		addToWord(src);
		CompoundCopyToWordEmitter dest = new CompoundCopyToWordEmitter();
		dest.ingestObjectDefinitionLocation(this);
		SSHAuthorityCopyToStringSource auth = new SSHAuthorityCopyToStringSource(config);
		auth.ingestObjectDefinitionLocation(this);
		dest.addPiece(auth);
		dest.addPiece(":");
		DestinationPathCopyToStringSource path = new DestinationPathCopyToStringSource();
		path.ingestObjectDefinitionLocation(this);
		dest.addPiece(path);
		addToWord(dest);
	}

	@Override
	protected void buildFromWords() {
		SCPCopyFromWordEmitter cmd = new SCPCopyFromWordEmitter(config);
		cmd.ingestObjectDefinitionLocation(this);
		addFromWord(cmd);
		CompoundCopyFromWordEmitter src = new CompoundCopyFromWordEmitter();
		src.ingestObjectDefinitionLocation(this);
		SSHAuthorityCopyFromStringSource auth = new SSHAuthorityCopyFromStringSource(config);
		auth.ingestObjectDefinitionLocation(auth);
		src.addPiece(auth);
		src.addPiece(":");
		SourcePathCopyFromStringSource path = new SourcePathCopyFromStringSource();
		path.ingestObjectDefinitionLocation(this);
		src.addPiece(path);
		addFromWord(src);
		DestinationPathCopyFromWordEmitter dest = new DestinationPathCopyFromWordEmitter();
		dest.ingestObjectDefinitionLocation(this);
		addFromWord(dest);
	}

}
