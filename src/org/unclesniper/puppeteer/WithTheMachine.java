package org.unclesniper.puppeteer;

public class WithTheMachine extends AbstractWithAnyMachine {

	public WithTheMachine() {}

	@Override
	protected void performImpl(NetworkStepInfo info) throws PuppetException {
		MachinePredicate predicate = getThePredicate();
		Iterable<MachineStep> steps = getSteps();
		try {
			int machineCount = 0;
			Machine theMachine = null;
			MachineStep.MachineStepInfo minfo = new MachineStep.MachineStepInfo(info);
			Network network = info.getNetwork();
			for(Machine machine : network.getMachines()) {
				minfo.setMachine(machine);
				if(!predicate.test(minfo))
					continue;
				theMachine = machine;
				++machineCount;
			}
			if(machineCount != 1)
				throw new AmbiguousMachineException(machineCount, network);
			minfo.setMachine(theMachine);
			for(MachineStep step : steps)
				step.perform(minfo);
		}
		catch(PuppetException pe) {
			GeneralStep.addFrame(pe, this);
			throw pe;
		}
	}

}
