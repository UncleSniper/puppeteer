package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;

public abstract class AbstractNetworkStepPairEmitter extends AbstractTraceable implements NetworkStepPairEmitter {

	public AbstractNetworkStepPairEmitter() {}

	protected abstract void buildMapImpl(NetworkStep.NetworkStepInfo info, BiConsumer<String, String> sink)
			throws PuppetException;

	@Override
	public void buildMap(NetworkStep.NetworkStepInfo info, BiConsumer<String, String> sink) throws PuppetException {
		try {
			buildMapImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
