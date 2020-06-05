package org.unclesniper.puppeteer;

public interface ExitStatusCapture extends Traceable {

	void captureExitStatus(int status) throws PuppetException;

}
