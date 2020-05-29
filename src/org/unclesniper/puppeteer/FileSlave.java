package org.unclesniper.puppeteer;

public interface FileSlave extends Traceable {

	String newTempFile(Machine machine) throws PuppetException;

	void deleteFile(Machine machine, String file) throws PuppetException;

}
