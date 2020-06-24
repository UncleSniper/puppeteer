package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkStepMachineStep")
public class NetworkStepMachineStep extends AbstractMachineStep {

	private NetworkStep step;

	public NetworkStepMachineStep() {}

	public NetworkStepMachineStep(NetworkStep step) {
		this.step = step;
	}

	public NetworkStep getStep() {
		return step;
	}

	public void setStep(NetworkStep step) {
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
