package org.unclesniper.puppeteer;

public abstract class AbstractExecPredicate extends AbstractTraceable implements ExecPredicate {

	public AbstractExecPredicate() {}

	protected abstract boolean testImpl(ExecSlave.ExecInfo info) throws PuppetException;

	@Override
	public boolean test(ExecSlave.ExecInfo info) throws PuppetException {
		try {
			return testImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected void printAbstractExecPredicateTo(StructSink sink, boolean more) {}

}
