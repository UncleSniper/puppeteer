package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("withEachMachine")
public class WithEachMachine extends AbstractWithAnyMachine {

	private boolean requiresAtLeastOne;

	public WithEachMachine() {}

	public boolean isRequiresAtLeastOne() {
		return requiresAtLeastOne;
	}

	public void setRequiresAtLeastOne(boolean requiresAtLeastOne) {
		this.requiresAtLeastOne = requiresAtLeastOne;
	}

	@Override
	protected void performImpl(NetworkStepInfo info) throws PuppetException {
		MachinePredicate predicate = getThePredicate();
		Iterable<MachineStep> steps = getSteps();
		boolean hasOne = false;
		MachineStep.MachineStepInfo minfo = new MachineStep.MachineStepInfo(info);
		Network network = info.getNetwork();
		for(Machine machine : network.getMachines()) {
			minfo.setMachine(machine);
			if(!predicate.test(minfo))
				continue;
			hasOne = true;
			for(MachineStep step : steps)
				step.perform(minfo);
		}
		if(!hasOne && requiresAtLeastOne)
			throw new NoMatchingMachineException(network, predicate);
	}

}
