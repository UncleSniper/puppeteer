package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractMachineStepWordEmitter extends AbstractTraceable implements MachineStepWordEmitter {

	public AbstractMachineStepWordEmitter() {}

	protected abstract void buildArgvImpl(MachineStep.MachineStepInfo info, Consumer<String> sink)
			throws PuppetException;

	@Override
	public void buildArgv(MachineStep.MachineStepInfo info, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
