package org.unclesniper.puppeteer;

public class MissingStepInfoException extends PuppetException {

	public MissingStepInfoException() {
		super("Cannot access step context as none is present");
	}

}
