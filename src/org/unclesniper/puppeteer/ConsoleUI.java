package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.StringUtils;

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
	public void warn(Object object, String[] message) {
		if(message == null || message.length == 0)
			return;
		Traceable traceable = object instanceof Traceable ? (Traceable)object : null;
		String pfx = object == null ? "" : object.getClass().getName();
		pfx += Traceable.makeLocation(traceable, " (defined at ", ")", "");
		if(object != null)
			pfx += ": ";
		pfx += "Warning: ";
		String blanks = StringUtils.repeatChar(' ', pfx.length());
		for(int i = 0; i < message.length; ++i) {
			System.err.print(i > 0 ? blanks : pfx);
			System.err.println(message[i]);
		}
		System.err.flush();
	}

	@Override
	public void info(Object object, String[] message, boolean verbose) {
		if(message == null || message.length == 0)
			return;
		if(verbose && !this.verbose)
			return;
		Traceable traceable = object instanceof Traceable ? (Traceable)object : null;
		String pfx = object == null ? "" : object.getClass().getName();
		pfx += Traceable.makeLocation(traceable, " (defined at ", ")", "");
		if(object != null)
			pfx += ": ";
		String blanks = StringUtils.repeatChar(' ', pfx.length());
		for(int i = 0; i < message.length; ++i) {
			System.out.print(i > 0 ? blanks : pfx);
			System.out.println(message[i]);
		}
		System.out.flush();
	}

}
