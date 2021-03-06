package org.unclesniper.puppeteer;

public abstract class AbstractStep extends AbstractGeneralStep implements Step {

	public AbstractStep() {}

	protected abstract void performImpl(StepInfo info) throws PuppetException;

	@Override
	public String getStepQualifier() {
		return null;
	}

	@Override
	public void perform(StepInfo info) throws PuppetException {
		info.getUI().executingStep(this);
		try {
			performImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
