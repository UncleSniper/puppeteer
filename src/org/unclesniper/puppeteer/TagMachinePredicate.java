package org.unclesniper.puppeteer;

public class TagMachinePredicate extends AbstractTagPredicate implements MachinePredicate {

	public TagMachinePredicate() {}

	@Override
	public boolean test(MachineStep.MachineStepInfo info) throws PuppetException {
		return isSatisfied(info.getMachine()::hasTag);
	}

}
