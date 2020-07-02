package org.unclesniper.puppeteer;

public abstract class AbstractCopyFromPredicate extends AbstractTraceable implements CopyFromPredicate {

	public AbstractCopyFromPredicate() {}

	abstract protected boolean testImpl(CopySlave.CopyFromInfo info) throws PuppetException;

	@Override
	public boolean test(CopySlave.CopyFromInfo info) throws PuppetException {
		try {
			return testImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected void printAbstractCopyFromPredicateTo(StructSink sink, boolean more) {}

}
