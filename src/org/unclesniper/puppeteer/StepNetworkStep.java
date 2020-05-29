package org.unclesniper.puppeteer;

public class StepNetworkStep extends AbstractNetworkStep {

	private Step step;

	public StepNetworkStep() {}

	public StepNetworkStep(Step step) {
		this.step = step;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	@Override
	protected void performImpl(NetworkStepInfo info) throws PuppetException {
		if(step != null)
			step.perform(info);
	}

	@Override
	public String getTraceObjectDefinitionLocation() {
		String location = super.getTraceObjectDefinitionLocation();
		return location == null && step != null ? step.getTraceObjectDefinitionLocation() : location;
	}

}
