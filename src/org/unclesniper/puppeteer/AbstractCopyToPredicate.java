package org.unclesniper.puppeteer;

public abstract class AbstractCopyToPredicate extends AbstractTraceable implements CopyToPredicate {

	public AbstractCopyToPredicate() {}

	protected abstract boolean testImpl(CopySlave.CopyToInfo info) throws PuppetException;

	@Override
	public boolean test(CopySlave.CopyToInfo info) throws PuppetException {
		try {
			return testImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected void printAbstractCopyToPredicateTo(StructSink sink, boolean more) {}

}
