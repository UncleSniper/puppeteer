package org.unclesniper.puppeteer;

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

}
