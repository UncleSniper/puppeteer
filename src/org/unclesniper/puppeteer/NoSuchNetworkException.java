package org.unclesniper.puppeteer;

public class NoSuchNetworkException extends AbstractStepTracingException {

	private final String networkName;

	public NoSuchNetworkException(String networkName) {
		super("There is no network by name of '" + networkName + '\'');
		this.networkName = networkName;
	}

	public String getNetworkName() {
		return networkName;
	}

}
