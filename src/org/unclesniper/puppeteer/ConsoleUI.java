package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.StringUtils;

public class ConsoleUI implements PuppeteerUI {

	private boolean verbose;

	private boolean nativeTrace;

	public ConsoleUI() {}

	public ConsoleUI(boolean verbose) {
		this(verbose, false);
	}

	public ConsoleUI(boolean verbose, boolean nativeTrace) {
		this.verbose = verbose;
		this.nativeTrace = nativeTrace;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isNativeTrace() {
		return nativeTrace;
	}

	public void setNativeTrace(boolean nativeTrace) {
		this.nativeTrace = nativeTrace;
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

	@Override
	public void error(Throwable t) {
		if(t == null)
			return;
		System.err.print("Error executing plan: ");
		ExceptionSink.describe(t, new TextualExceptionSink(new PrintStreamStructSink(System.err), nativeTrace));
		System.err.flush();
	}

}
