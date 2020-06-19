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
			System.out.print(qualifier + ": ");
		System.out.println(title);
		System.out.flush();
	}

	@Override
	public void warn(Object object, String message) {
		Traceable traceable = object instanceof Traceable ? (Traceable)object : null;
		String location = traceable == null ? null : traceable.getTraceObjectDefinitionLocation();
		if(object != null)
			System.err.print(object.getClass().getName());
		System.err.print(Traceable.makeLocation(traceable, " (defined at ", ")", ""));
		if(object != null)
			System.err.print(": ");
		System.err.print("Warning: ");
		System.err.println(message);
		System.err.flush();
	}

}
