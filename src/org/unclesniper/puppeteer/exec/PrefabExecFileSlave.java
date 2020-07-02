package org.unclesniper.puppeteer.exec;

import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.MachineStep;
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
	protected String newTempFileImpl(MachineStep.MachineStepInfo stepInfo, Machine machine) throws PuppetException {
		if(!hasTempFileWords())
			buildTempFileWords();
		return super.newTempFileImpl(stepInfo, machine);
	}

	@Override
	protected void deleteFileImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, String file)
			throws PuppetException {
		if(!hasDeleteFileWords())
			buildDeleteFileWords();
		super.deleteFileImpl(stepInfo, machine, file);
	}

}
