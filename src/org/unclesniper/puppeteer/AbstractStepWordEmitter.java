package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractStepWordEmitter extends AbstractTraceable implements StepWordEmitter {

	public AbstractStepWordEmitter() {}

	protected abstract void buildArgvImpl(Step.StepInfo info, Consumer<String> sink) throws PuppetException;

	@Override
	public void buildArgv(Step.StepInfo info, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
