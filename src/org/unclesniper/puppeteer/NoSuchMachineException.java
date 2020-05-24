package org.unclesniper.puppeteer;

public class NoSuchMachineException extends AbstractStepTracingException {

	private final String machineName;

	private final Network network;

	public NoSuchMachineException(String machineName, Network network) {
		super("There is no machine by name of '" + machineName + '\''
				+ Network.makeMessage(network, " in network ", ""));
		this.machineName = machineName;
		this.network = network;
	}

	public String getMachineName() {
		return machineName;
	}

	public Network getNetwork() {
		return network;
	}

}
