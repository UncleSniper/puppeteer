package org.unclesniper.puppeteer.exec;

import java.util.Map;
import org.unclesniper.puppeteer.Argv;
import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.ExecControl;
import org.unclesniper.puppeteer.MachineStep;
import org.unclesniper.puppeteer.ExecExecSlave;
import org.unclesniper.puppeteer.PuppetException;

public abstract class PrefabExecExecSlave extends ExecExecSlave {

	public PrefabExecExecSlave() {}

	public PrefabExecExecSlave(Machine execHost) {
		super(execHost);
	}

	protected abstract void buildWords();

	@Override
	protected ExecControl execute(MachineStep.MachineStepInfo stepInfo, Machine machine, Argv argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException {
		if(!hasWords())
			buildWords();
		return super.execute(stepInfo, machine, argv, workdir, environ, flags);
	}

}
