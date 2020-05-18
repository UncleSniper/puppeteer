package org.unclesniper.puppeteer;

import java.io.IOException;

public class LocalCopyFailedException extends IOPuppetException {

	public LocalCopyFailedException(String path, boolean towardsRemote, IOException cause) {
		super("Failed to copy file '" + path + "' " + (towardsRemote ? "to" : "from") + " machine", cause);
	}

}
