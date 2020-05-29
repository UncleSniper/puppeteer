package org.unclesniper.puppeteer;

import java.io.IOException;

public class NewTempFileIOPuppetException extends IOPuppetException
		implements CommandOutputBuffer, CommandOutputHolderHolder {

	private final Machine machine;

	private ListCommandOutputBuffer outputBuffer;

	public NewTempFileIOPuppetException(Machine machine, IOException cause) {
		super("Failed to create temporary file" + Machine.makeMessage(machine, " on ", "")
				+ Traceable.makeLocation(machine, " (machine defined at ", ")", ""), cause);
		this.machine = machine;
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
