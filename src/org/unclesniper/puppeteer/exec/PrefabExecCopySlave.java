package org.unclesniper.puppeteer.exec;

import org.unclesniper.puppeteer.InFile;
import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.OutFile;
import org.unclesniper.puppeteer.MachineStep;
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
	protected void copyTo(MachineStep.MachineStepInfo stepInfo, Machine machine, InFile source, String destination)
			throws PuppetException {
		if(!hasToWords())
			buildToWords();
		super.copyTo(stepInfo, machine, source, destination);
	}

	@Override
	protected void copyFrom(MachineStep.MachineStepInfo stepInfo, Machine machine, String source, OutFile destination)
			throws PuppetException {
		if(!hasFromWords())
			buildFromWords();
		super.copyFrom(stepInfo, machine, source, destination);
	}

}
