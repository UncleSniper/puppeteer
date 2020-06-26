package org.unclesniper.puppeteer;

public abstract class AbstractMachinePredicate extends AbstractTraceable implements MachinePredicate {

	public AbstractMachinePredicate() {}

	protected abstract boolean testImpl(MachineStep.MachineStepInfo info) throws PuppetException;

	@Override
	public boolean test(MachineStep.MachineStepInfo info) throws PuppetException {
		try {
			return testImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected void printAbstractMachinePredicateTo(StructSink sink, boolean more) {}

}
