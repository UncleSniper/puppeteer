package org.unclesniper.puppeteer;

public interface GeneralStep {

	String getStepTitle();

	String getStepQualifier();

	public static void addFrame(PuppetException exception, GeneralStep step) {
		if(exception instanceof StepTraceBuffer)
			((StepTraceBuffer)exception).addStepFrame(step);
	}

}
