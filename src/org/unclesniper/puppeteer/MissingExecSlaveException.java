package org.unclesniper.puppeteer;

public class MissingExecSlaveException extends PuppetException {

	private final Machine machine;

	public MissingExecSlaveException(Machine machine) {
		super("Machine" + Machine.makeMessage(machine, " ", "")
				+ Traceable.makeLocation(machine, " defined at ", "", "") + " has no ExecSlave");
		this.machine = machine;
	}

	public Machine getMachine() {
		return machine;
	}

}
