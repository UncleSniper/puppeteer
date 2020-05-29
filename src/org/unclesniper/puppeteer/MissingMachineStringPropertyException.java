package org.unclesniper.puppeteer;

public class MissingMachineStringPropertyException extends PuppetException {

	private final Machine machine;

	private final String property;

	public MissingMachineStringPropertyException(Machine machine, String property) {
		super("Required string property '" + property + "' is missing from machine"
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
