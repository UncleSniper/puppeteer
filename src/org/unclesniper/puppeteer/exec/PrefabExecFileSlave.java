package org.unclesniper.puppeteer.exec;

import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.ExecFileSlave;
import org.unclesniper.puppeteer.PuppetException;

public abstract class PrefabExecFileSlave extends ExecFileSlave {

	public PrefabExecFileSlave() {}

	public PrefabExecFileSlave(Machine execHost) {
		super(execHost);
	}

	protected abstract void buildTempFileWords();

	protected abstract void buildDeleteFileWords();

	@Override
	protected String newTempFileImpl(Machine machine) throws PuppetException {
		if(!hasTempFileWords())
			buildTempFileWords();
		return super.newTempFileImpl(machine);
	}

	@Override
	protected void deleteFileImpl(Machine machine, String file) throws PuppetException {
		if(!hasDeleteFileWords())
			buildDeleteFileWords();
		super.deleteFileImpl(machine, file);
	}

}
