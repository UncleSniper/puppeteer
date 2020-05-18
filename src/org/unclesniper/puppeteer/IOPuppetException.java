package org.unclesniper.puppeteer;

import java.io.IOException;

public class IOPuppetException extends PuppetException {

	public IOPuppetException(String message, IOException cause) {
		super(cause.getMessage() != null && cause.getMessage().length() > 0
				? message + ": " + cause.getMessage() : message, cause);
	}

}
