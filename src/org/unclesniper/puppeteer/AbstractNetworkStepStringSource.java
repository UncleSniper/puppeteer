package org.unclesniper.puppeteer;

public abstract class AbstractNetworkStepStringSource extends AbstractTraceable implements NetworkStepStringSource {

	public AbstractNetworkStepStringSource() {}

	protected abstract void buildStringImpl(NetworkStep.NetworkStepInfo info, StringBuilder sink)
			throws PuppetException;

	@Override
	public void buildString(NetworkStep.NetworkStepInfo info, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
