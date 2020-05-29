package org.unclesniper.puppeteer;

import java.io.IOException;

public class DownloadIOPuppetException extends IOPuppetException
		implements CommandOutputBuffer, CommandOutputHolderHolder {

	private final Machine sourceMachine;

	private final String sourcePath;

	private ListCommandOutputBuffer outputBuffer;

	public DownloadIOPuppetException(Machine sourceMachine, String sourcePath, IOException cause) {
		super("Failed to download file '" + sourcePath + '\''
				+ Machine.makeMessage(sourceMachine, " from ", "")
				+ Traceable.makeLocation(sourceMachine, " (machine defined at ", ")", ""), cause);
		this.sourceMachine = sourceMachine;
		this.sourcePath = sourcePath;
	}

	public Machine getSourceMachine() {
		return sourceMachine;
	}

	public String getSourcePath() {
		return sourcePath;
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
