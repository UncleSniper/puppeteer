package org.unclesniper.puppeteer;

public class StepMachineStep extends AbstractMachineStep {

	private Step step;

	public StepMachineStep() {}

	public StepMachineStep(Step step) {
		this.step = step;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	@Override
	protected void performImpl(MachineStepInfo info) throws PuppetException {
		if(step != null)
			step.perform(info);
	}

}
