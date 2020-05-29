package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public class Plan {

	private final List<Step> steps = new LinkedList<Step>();

	public Plan() {}

	public Iterable<Step> getSteps() {
		return steps;
	}

	public void addStep(Step step) {
		if(step != null)
			steps.add(step);
	}

	public void perform(PuppeteerUI ui, World world, ScopeLevel scope) throws PuppetException {
		if(scope == null)
			scope = new ScopeLevel(null);
		perform(new Step.StepInfo(ui, world, scope));
	}

	public void perform(Step.StepInfo info) throws PuppetException {
		for(Step step : steps)
			step.perform(info);
	}

}
