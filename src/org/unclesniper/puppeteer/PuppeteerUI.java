package org.unclesniper.puppeteer;

public interface PuppeteerUI {

	void executingStep(GeneralStep step);

	void warn(Object object, String[] message);

	void info(Object object, String[] message, boolean verbose);

	void error(Throwable t);

	default void warn(Object object, String message) {
		warn(object, new String[] {message});
	}

	default void info(Object object, String message, boolean verbose) {
		info(object, new String[] {message}, verbose);
	}

}
