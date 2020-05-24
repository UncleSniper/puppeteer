package org.unclesniper.puppeteer;

public abstract class AbstractMachineStep extends AbstractGeneralStep implements MachineStep {

	private Machine currentMachine;

	public AbstractMachineStep() {}

	protected abstract void performImpl(MachineStepInfo info) throws PuppetException;

	@Override
	public void perform(MachineStepInfo info) throws PuppetException {
		Machine oldMachine = currentMachine;
		try {
			currentMachine = info.getMachine();
			performImpl(info);
		}
		finally {
			currentMachine = oldMachine;
		}
	}

}
