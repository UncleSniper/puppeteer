package org.unclesniper.puppeteer;

public class NoMatchingNetworkException extends AbstractStepTracingException {

	public NoMatchingNetworkException() {
		super("No network matches the predicate");
	}

}
