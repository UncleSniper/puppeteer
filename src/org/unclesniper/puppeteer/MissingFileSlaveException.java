package org.unclesniper.puppeteer;

public class MissingFileSlaveException extends PuppetException {

	private final Machine machine;

	public MissingFileSlaveException(Machine machine) {
		super("Machine" + Machine.makeMessage(machine, " ", "")
				+ Traceable.makeLocation(machine, " defined at ", "", "") + " has no FileSlave");
		this.machine = machine;
	}

	public Machine getMachine() {
		return machine;
	}

}
