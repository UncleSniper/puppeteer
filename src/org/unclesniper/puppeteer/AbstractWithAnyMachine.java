package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public abstract class AbstractWithAnyMachine extends AbstractNetworkStep {

	private MachinePredicate predicate;

	private final List<MachineStep> steps = new LinkedList<MachineStep>();

	public AbstractWithAnyMachine() {}

	public MachinePredicate getPredicate() {
		return predicate;
	}

	protected MachinePredicate getThePredicate() {
		return predicate == null ? AnyMachinePredicate.instance : predicate;
	}

	public void setPredicate(MachinePredicate predicate) {
		this.predicate = predicate;
	}

	public Iterable<MachineStep> getSteps() {
		return steps;
	}

	public void addStep(MachineStep step) {
		if(step != null)
			steps.add(step);
	}

}
