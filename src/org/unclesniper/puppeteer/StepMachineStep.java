package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepMachineStep")
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

	@Override
	public String getTraceObjectDefinitionLocation() {
		String location = super.getTraceObjectDefinitionLocation();
		return location == null && step != null ? step.getTraceObjectDefinitionLocation() : location;
	}

}
