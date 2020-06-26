package org.unclesniper.puppeteer;

public abstract class AbstractNetworkPredicate extends AbstractTraceable implements NetworkPredicate {

	public AbstractNetworkPredicate() {}

	protected abstract boolean testImpl(NetworkStep.NetworkStepInfo info) throws PuppetException;

	@Override
	public boolean test(NetworkStep.NetworkStepInfo info) throws PuppetException {
		try {
			return testImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected void printAbstractNetworkPredicateTo(StructSink sink, boolean more) {}

}
