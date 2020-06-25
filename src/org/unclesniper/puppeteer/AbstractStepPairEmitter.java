package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;

public abstract class AbstractStepPairEmitter extends AbstractTraceable implements StepPairEmitter {

	public AbstractStepPairEmitter() {}

	protected abstract void buildMapImpl(Step.StepInfo info, BiConsumer<String, String> sink)
			throws PuppetException;

	@Override
	public void buildMap(Step.StepInfo info, BiConsumer<String, String> sink) throws PuppetException {
		try {
			buildMapImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
