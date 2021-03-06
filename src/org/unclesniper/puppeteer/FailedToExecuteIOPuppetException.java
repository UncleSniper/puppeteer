package org.unclesniper.puppeteer;

import java.io.IOException;

public class FailedToExecuteIOPuppetException extends IOPuppetException
		implements CommandOutputBuffer, CommandOutputHolderHolder {

	private final Iterable<String> argv;

	private final Machine machine;

	private ListCommandOutputBuffer outputBuffer;

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

	@Override
	public void addStdoutLine(String line) {
		if(outputBuffer == null)
			outputBuffer = new ListCommandOutputBuffer();
		outputBuffer.addStdoutLine(line);
	}

	@Override
	public void addStderrLine(String line) {
		if(outputBuffer == null)
			outputBuffer = new ListCommandOutputBuffer();
		outputBuffer.addStderrLine(line);
	}

	@Override
	public CommandOutputHolder getCommandOutput() {
		return outputBuffer == null ? EmptyCommandOutputHolder.instance : outputBuffer;
	}

}
