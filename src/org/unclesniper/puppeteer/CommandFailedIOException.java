package org.unclesniper.puppeteer;

import java.io.IOException;

public class CommandFailedIOException extends IOException {

	private final int exitStatus;

	public CommandFailedIOException(int exitStatus) {
		super("Command exited with non-zero status: " + exitStatus);
		this.exitStatus = exitStatus;
	}

	public int getExitStatus() {
		return exitStatus;
	}

}
