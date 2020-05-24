package org.unclesniper.puppeteer;

public class AmbiguousMachineException extends AbstractStepTracingException {

	private final Network network;

	private final int machineCount;

	public AmbiguousMachineException(int machineCount, Network network) {
		super("Expected a single machine to match the predicate" + Network.makeMessage(network, " in network ", "")
				+ ", but " + machineCount + " matched");
		this.network = network;
		this.machineCount = machineCount;
	}

	public Network getNetwork() {
		return network;
	}

	public int getMachineCount() {
		return machineCount;
	}

}
