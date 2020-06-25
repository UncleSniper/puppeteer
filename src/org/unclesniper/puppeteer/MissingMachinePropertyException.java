package org.unclesniper.puppeteer;

public abstract class MissingMachinePropertyException extends PuppetException {

	private final Machine machine;

	private final String property;

	public MissingMachinePropertyException(Machine machine, String type, String property) {
		super("Required " + type + " property '" + property + "' is missing from machine"
				+ Machine.makeMessage(machine, " ", "")
				+ Traceable.makeLocation(machine, " (machine defined at ", ")", ""));
		this.machine = machine;
		this.property = property;
	}

	public Machine getMachine() {
		return machine;
	}

	public String getProperty() {
		return property;
	}

}
