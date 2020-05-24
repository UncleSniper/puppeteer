package org.unclesniper.puppeteer;

import java.util.Deque;
import java.util.LinkedList;

public abstract class AbstractStepTracingException extends PuppetException
		implements StepTraceBuffer, StepTraceHolder {

	private final Deque<GeneralStep> steps = new LinkedList<GeneralStep>();

	public AbstractStepTracingException(String message) {
		super(message);
	}

	public AbstractStepTracingException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public void addStepFrame(GeneralStep step) {
		if(step != null)
			steps.addLast(step);
	}

	@Override
	public boolean hasStepFrames() {
		return !steps.isEmpty();
	}

	@Override
	public Iterable<GeneralStep> getStepFrames() {
		return steps;
	}

}
