package org.unclesniper.puppeteer;

public class AmbiguousMachineNameException extends PuppetException {

	private final String machineName;

	private final Network network;

	public AmbiguousMachineNameException(String machineName, Network network) {
		super("Machine name '" + machineName + "' is ambiguous" + Network.makeMessage(network, " in network ", ""));
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
