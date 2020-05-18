package org.unclesniper.puppeteer;

import java.io.IOException;

public class UploadIOPuppetException extends IOPuppetException
		implements CommandOutputBuffer, CommandOutputHolderHolder {

	private final Machine destinationMachine;

	private final String destinationPath;

	private ListCommandOutputBuffer outputBuffer;

	public UploadIOPuppetException(Machine destinationMachine, String destinationPath, IOException cause) {
		super("Failed to upload to file '" + destinationPath + '\''
				+ Machine.makeMessage(destinationMachine, " on ", ""), cause);
		this.destinationMachine = destinationMachine;
		this.destinationPath = destinationPath;
	}

	public Machine getDestinationMachine() {
		return destinationMachine;
	}

	public String getDestinationPath() {
		return destinationPath;
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
