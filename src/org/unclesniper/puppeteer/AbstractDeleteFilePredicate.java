package org.unclesniper.puppeteer;

public abstract class AbstractDeleteFilePredicate extends AbstractTraceable implements DeleteFilePredicate {

	public AbstractDeleteFilePredicate() {}

	protected abstract boolean testImpl(FileSlave.DeleteFileInfo info) throws PuppetException;

	@Override
	public boolean test(FileSlave.DeleteFileInfo info) throws PuppetException {
		try {
			return testImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected void printAbstractDeleteFilePredicateTo(StructSink sink, boolean more) {}

}
