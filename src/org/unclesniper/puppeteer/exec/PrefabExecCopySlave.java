package org.unclesniper.puppeteer.exec;

import org.unclesniper.puppeteer.InFile;
import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.OutFile;
import org.unclesniper.puppeteer.ExecCopySlave;
import org.unclesniper.puppeteer.PuppetException;

public abstract class PrefabExecCopySlave extends ExecCopySlave {

	public PrefabExecCopySlave() {}

	public PrefabExecCopySlave(Machine execHost) {
		super(execHost);
	}

	protected abstract void buildToWords();

	protected abstract void buildFromWords();

	@Override
	protected void copyTo(Machine machine, InFile source, String destination) throws PuppetException {
		if(!hasToWords())
			buildToWords();
		super.copyTo(machine, source, destination);
	}

	@Override
	protected void copyFrom(Machine machine, String source, OutFile destination) throws PuppetException {
		if(!hasFromWords())
			buildFromWords();
		super.copyFrom(machine, source, destination);
	}

}
