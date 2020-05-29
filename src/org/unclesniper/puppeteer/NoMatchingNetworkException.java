package org.unclesniper.puppeteer;

public class NoMatchingNetworkException extends PuppetException {

	private final NetworkPredicate predicate;

	public NoMatchingNetworkException(NetworkPredicate predicate) {
		super("No network matches the predicate"
				+ Traceable.makeLocation(predicate, " (predicate defined at ", ")", ""));
		this.predicate = predicate;
	}

	public NetworkPredicate getPredicate() {
		return predicate;
	}

}
