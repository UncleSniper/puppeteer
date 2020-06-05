package org.unclesniper.puppeteer;

import java.io.IOException;

public class FailedToExecuteIOPuppetException extends IOPuppetException {

	private final Iterable<String> argv;

	private final Machine machine;

	public FailedToExecuteIOPuppetException(Iterable<String> argv, Machine machine, IOException cause) {
		super("Failed to execute command on machine" + Machine.makeMessage(machine, " ", "")
				+ Traceable.makeLocation(machine, " defined at ", "", "") + ": "
				+ BashWordQuoter.instance.asString(argv), cause);
		this.argv = argv;
		this.machine = machine;
	}

	public Iterable<String> getArgv() {
		return argv;
	}

	public Machine getMachine() {
		return machine;
	}

}
