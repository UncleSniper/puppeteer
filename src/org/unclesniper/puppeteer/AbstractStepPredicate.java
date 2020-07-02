package org.unclesniper.puppeteer;

public abstract class AbstractStepPredicate extends AbstractTraceable implements StepPredicate {

	public AbstractStepPredicate() {}

	protected abstract boolean testImpl(Step.StepInfo info) throws PuppetException;

	@Override
	public boolean test(Step.StepInfo info) throws PuppetException {
		try {
			return testImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected void printAbstractStepPredicateTo(StructSink sink, boolean more) {}

}
