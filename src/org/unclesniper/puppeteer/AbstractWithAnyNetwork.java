package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public abstract class AbstractWithAnyNetwork extends AbstractStep {

	private NetworkPredicate predicate;

	private final List<NetworkStep> steps = new LinkedList<NetworkStep>();

	public AbstractWithAnyNetwork() {}

	public NetworkPredicate getPredicate() {
		return predicate;
	}

	protected final NetworkPredicate getThePredicate() {
		if(predicate == null)
			throw new IllegalStateException("No predicate set");
		return predicate;
	}

	public void setPredicate(NetworkPredicate predicate) {
		this.predicate = predicate;
	}

	public Iterable<NetworkStep> getSteps() {
		return steps;
	}

	public void addStep(NetworkStep step) {
		if(step != null)
			steps.add(step);
	}

}
