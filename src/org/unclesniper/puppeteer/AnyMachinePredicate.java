package org.unclesniper.puppeteer;

public class AnyMachinePredicate implements MachinePredicate {

	public static final AnyMachinePredicate instance = new AnyMachinePredicate();

	public AnyMachinePredicate() {}

	@Override
	public boolean test(MachineStep.MachineStepInfo info) {
		return true;
	}

}
