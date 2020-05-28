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
		catch(PuppetException pe) {
			GeneralStep.addFrame(pe, this);
			throw pe;
		}
		finally {
			currentMachine = oldMachine;
		}
	}

	@Override
	public String getStepQualifier() {
		if(currentMachine == null)
			return null;
		String name = currentMachine.getHostname();
		return name == null ? null : "machine '" + name + '\'';
	}

}
