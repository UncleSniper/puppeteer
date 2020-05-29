package org.unclesniper.puppeteer;

public class NoMatchingMachineException extends PuppetException {

	private final Network network;

	private final MachinePredicate predicate;

	public NoMatchingMachineException(Network network, MachinePredicate predicate) {
		super("No machine matches the predicate" + Network.makeMessage(network, " in network ", "")
				+ Traceable.makeLocation(network, " (network defined at ", ")", "")
				+ Traceable.makeLocation(predicate, " (predicate defined at ", ")", ""));
		this.network = network;
		this.predicate = predicate;
	}

	public Network getNetwork() {
		return network;
	}

	public MachinePredicate getPredicate() {
		return predicate;
	}

}
