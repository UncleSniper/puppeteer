package org.unclesniper.puppeteer;

public abstract class AbstractStep extends AbstractGeneralStep implements Step {

	public AbstractStep() {}

	@Override
	public String getStepQualifier() {
		return null;
	}

}
