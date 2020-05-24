package org.unclesniper.puppeteer;

public class ConsoleUI implements PuppeteerUI {

	private boolean verbose;

	public ConsoleUI() {}

	public ConsoleUI(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	@Override
	public void executingStep(GeneralStep step) {
		if(!verbose)
			return;
		String title = step.getStepTitle();
		if(title == null)
			return;
		String qualifier = step.getStepQualifier();
		if(qualifier != null)
			System.out.println(qualifier + ": ");
		System.out.println(title);
		System.out.flush();
	}

}
