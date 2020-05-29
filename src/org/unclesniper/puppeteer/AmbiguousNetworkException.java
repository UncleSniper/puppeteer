package org.unclesniper.puppeteer;

public class AmbiguousNetworkException extends PuppetException {

	private final int networkCount;

	public AmbiguousNetworkException(int networkCount) {
		super("Expected a single network to match the predicate, but " + networkCount + " matched");
		this.networkCount = networkCount;
	}

	public int getNetworkCount() {
		return networkCount;
	}

}
