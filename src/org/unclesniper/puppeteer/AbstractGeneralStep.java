package org.unclesniper.puppeteer;

public abstract class AbstractGeneralStep implements GeneralStep {

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
