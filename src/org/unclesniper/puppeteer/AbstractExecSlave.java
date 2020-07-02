package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.Collection;

public abstract class AbstractExecSlave extends AbstractTraceable implements ExecSlave {

	public AbstractExecSlave() {}

	protected abstract ExecControl executeImpl(MachineStep.MachineStepInfo stepInfo, Machine machine,
			Collection<String> argv, String workdir, Map<String, String> environ, int flags)
			throws PuppetException;

	@Override
	public ExecControl execute(MachineStep.MachineStepInfo stepInfo, Machine machine, Collection<String> argv,
			String workdir, Map<String, String> environ, int flags) throws PuppetException {
		try {
			return executeImpl(stepInfo, machine, argv, workdir, environ, flags);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract ExecControl executeImpl(MachineStep.MachineStepInfo stepInfo, Machine machine,
			String[] argv, String workdir, Map<String, String> environ, int flags) throws PuppetException;

	@Override
	public ExecControl execute(MachineStep.MachineStepInfo stepInfo, Machine machine, String[] argv,
			String workdir, Map<String, String> environ, int flags) throws PuppetException {
		try {
			return executeImpl(stepInfo, machine, argv, workdir, environ, flags);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
