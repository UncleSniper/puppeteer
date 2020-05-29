package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractNetworkStepWordEmitter extends AbstractTraceable implements NetworkStepWordEmitter {

	public AbstractNetworkStepWordEmitter() {}

	protected abstract void buildArgvImpl(NetworkStep.NetworkStepInfo info, Consumer<String> sink)
			throws PuppetException;

	@Override
	public void buildArgv(NetworkStep.NetworkStepInfo info, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
