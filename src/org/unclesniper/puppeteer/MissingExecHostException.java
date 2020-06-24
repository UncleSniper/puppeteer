package org.unclesniper.puppeteer;

public class MissingExecHostException extends PuppetException {

	public MissingExecHostException() {
		super("Cannot access execHost as none is present");
	}

}
