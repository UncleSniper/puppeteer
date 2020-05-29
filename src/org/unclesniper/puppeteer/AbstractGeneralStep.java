package org.unclesniper.puppeteer;

public abstract class AbstractGeneralStep extends AbstractTraceable implements GeneralStep {

	private String stepTitle;

	public AbstractGeneralStep() {}

	@Override
	public String getStepTitle() {
		return stepTitle;
	}

	public void setStepTitle(String stepTitle) {
		this.stepTitle = stepTitle;
	}

}
