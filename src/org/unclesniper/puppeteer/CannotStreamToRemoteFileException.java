package org.unclesniper.puppeteer;

public class CannotStreamToRemoteFileException extends PuppetException {

	public CannotStreamToRemoteFileException() {
		super("Broken network configuration: Cannot issue OutputStream to remote file");
	}

}
