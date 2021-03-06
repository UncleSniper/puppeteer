package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("withNetwork")
public class WithNetwork extends AbstractStep {

	private String name;

	private final List<NetworkStep> steps = new LinkedList<NetworkStep>();

	public WithNetwork() {}

	public WithNetwork(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Iterable<NetworkStep> getSteps() {
		return steps;
	}

	public void addStep(NetworkStep step) {
		if(step != null)
			steps.add(step);
	}

	public void addStep(Step step) {
		if(step != null)
			steps.add(new StepNetworkStep(step));
	}

	@Override
	protected void performImpl(StepInfo info) throws PuppetException {
		if(name == null)
			throw new IllegalStateException("No network name set");
		Network network = info.getWorld().getNetwork(name);
		if(network == null)
			throw new NoSuchNetworkException(name);
		NetworkStep.NetworkStepInfo ninfo = new NetworkStep.NetworkStepInfo(info, network);
		for(NetworkStep step : steps)
			step.perform(ninfo);
	}

}
