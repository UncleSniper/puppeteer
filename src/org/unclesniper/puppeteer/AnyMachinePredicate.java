package org.unclesniper.puppeteer;

public class AnyMachinePredicate extends AbstractMachinePredicate {

	public static final AnyMachinePredicate instance = new AnyMachinePredicate();

	public AnyMachinePredicate() {}

	@Override
	protected boolean testImpl(MachineStep.MachineStepInfo info) {
		return true;
	}

}
