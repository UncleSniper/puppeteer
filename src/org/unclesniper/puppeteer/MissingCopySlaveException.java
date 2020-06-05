package org.unclesniper.puppeteer;

public class MissingCopySlaveException extends PuppetException {

	private final Machine machine;

	public MissingCopySlaveException(Machine machine) {
		super("Machine" + Machine.makeMessage(machine, " ", "")
				+ Traceable.makeLocation(machine, " defined at ", "", "") + " has no CopySlave");
		this.machine = machine;
	}

	public Machine getMachine() {
		return machine;
	}

}
