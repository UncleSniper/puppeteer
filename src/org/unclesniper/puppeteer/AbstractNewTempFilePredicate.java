package org.unclesniper.puppeteer;

public abstract class AbstractNewTempFilePredicate extends AbstractTraceable implements NewTempFilePredicate {

	public AbstractNewTempFilePredicate() {}

	protected abstract boolean testImpl(FileSlave.NewTempFileInfo info) throws PuppetException;

	@Override
	public boolean test(FileSlave.NewTempFileInfo info) throws PuppetException {
		try {
			return testImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected void printAbstractNewTempFilePredicateTo(StructSink sink, boolean more) {}

}
