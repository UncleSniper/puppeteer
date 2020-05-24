package org.unclesniper.puppeteer;

public class AmbiguousNetworkNameException extends PuppetException {

	private final String networkName;

	public AmbiguousNetworkNameException(String networkName) {
		super("Network name '" + networkName + "' is ambiguous");
		this.networkName = networkName;
	}

	public String getNetworkName() {
		return networkName;
	}

}
