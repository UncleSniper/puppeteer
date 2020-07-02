package org.unclesniper.puppeteer;

public abstract class AbstractScopePredicate extends AbstractTraceable implements ScopePredicate {

	public AbstractScopePredicate() {}

	protected abstract boolean testImpl(ScopeLevel scope) throws PuppetException;

	@Override
	public boolean test(ScopeLevel scope) throws PuppetException {
		try {
			return testImpl(scope);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected void printAbstractScopePredicateTo(StructSink sink, boolean more) {}

}
