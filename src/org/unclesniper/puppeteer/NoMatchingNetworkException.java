package org.unclesniper.puppeteer;

public class NoMatchingNetworkException extends PuppetException {

	public NoMatchingNetworkException() {
		super("No network matches the predicate");
	}

}
