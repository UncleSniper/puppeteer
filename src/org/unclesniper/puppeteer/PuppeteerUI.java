package org.unclesniper.puppeteer;

public interface PuppeteerUI {

	void executingStep(GeneralStep step);

	void warn(Object object, String message);

}
