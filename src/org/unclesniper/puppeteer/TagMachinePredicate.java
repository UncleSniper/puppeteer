package org.unclesniper.puppeteer;

public class TagMachinePredicate extends AbstractTagPredicate implements MachinePredicate {

	public TagMachinePredicate() {}

	@Override
	public boolean test(MachineStep.MachineStepInfo info) throws PuppetException {
		try {
			return isSatisfied(info.getMachine()::hasTag);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
