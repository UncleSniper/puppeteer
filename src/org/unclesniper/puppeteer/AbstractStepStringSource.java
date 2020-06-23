package org.unclesniper.puppeteer;

public abstract class AbstractStepStringSource extends AbstractTraceable implements StepStringSource {

	public AbstractStepStringSource() {}

	protected abstract void buildStringImpl(Step.StepInfo info, StringBuilder sink) throws PuppetException;

	@Override
	public void buildString(Step.StepInfo info, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
