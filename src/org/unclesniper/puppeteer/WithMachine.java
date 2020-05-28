package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public class WithMachine extends AbstractNetworkStep {

	private String hostname;

	private final List<MachineStep> steps = new LinkedList<MachineStep>();

	public WithMachine() {}

	public WithMachine(String hostname) {
		this.hostname = hostname;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Iterable<MachineStep> getSteps() {
		return steps;
	}

	public void addStep(MachineStep step) {
		if(step != null)
			steps.add(step);
	}

	@Override
	protected void performImpl(NetworkStepInfo info) throws PuppetException {
		if(hostname == null)
			throw new IllegalStateException("No hostname set");
		Network network = info.getNetwork();
		Machine machine = network.getMachine(hostname);
		if(machine == null)
			throw new NoSuchMachineException(hostname, network);
		MachineStep.MachineStepInfo minfo = new MachineStep.MachineStepInfo(info, machine);
		for(MachineStep step : steps)
			step.perform(minfo);
	}

}
