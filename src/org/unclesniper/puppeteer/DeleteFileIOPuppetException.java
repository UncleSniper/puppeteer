package org.unclesniper.puppeteer;

import java.io.IOException;

public class DeleteFileIOPuppetException extends IOPuppetException
		implements CommandOutputBuffer, CommandOutputHolderHolder {

	private final Machine machine;

	private final String offendingFile;

	private ListCommandOutputBuffer outputBuffer;

	public DeleteFileIOPuppetException(Machine machine, String offendingFile, IOException cause) {
		super("failed to delete file '" + offendingFile + '\'' + Machine.makeMessage(machine, " on ", ""), cause);
		this.machine = machine;
		this.offendingFile = offendingFile;
	}

	public Machine getMachine() {
		return machine;
	}

	public String getOffendingFile() {
		return offendingFile;
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
