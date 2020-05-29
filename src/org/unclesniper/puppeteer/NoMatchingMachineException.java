package org.unclesniper.puppeteer;

public class NoMatchingMachineException extends PuppetException {

	private final Network network;

	public NoMatchingMachineException(Network network) {
		super("No machine matches the predicate" + Network.makeMessage(network, " in network ", ""));
		this.network = network;
	}

	public Network getNetwork() {
		return network;
	}

}
