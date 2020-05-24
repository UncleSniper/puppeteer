package org.unclesniper.puppeteer;

public interface StepTraceHolder {

	boolean hasStepFrames();

	Iterable<GeneralStep> getStepFrames();

}
