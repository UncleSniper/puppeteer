package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;

public abstract class AbstractMachineStepPairEmitter extends AbstractTraceable implements MachineStepPairEmitter {

	public AbstractMachineStepPairEmitter() {}

	protected abstract void buildMapImpl(MachineStep.MachineStepInfo info, BiConsumer<String, String> sink)
			throws PuppetException;

	@Override
	public void buildMap(MachineStep.MachineStepInfo info, BiConsumer<String, String> sink) throws PuppetException {
		try {
			buildMapImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
