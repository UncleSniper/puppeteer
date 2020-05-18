package org.unclesniper.puppeteer;

import java.io.IOException;

public class LocalExecFailedException extends IOPuppetException {

	public LocalExecFailedException(String[] argv, IOException cause) {
		super("Failed to start local process (" + BashWordQuoter.instance.asString(argv) + ')', cause);
	}

}
