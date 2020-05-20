package org.unclesniper.puppeteer;

public interface FileSlave {

	String newTempFile(Machine machine) throws PuppetException;

	void deleteFile(Machine machine, String file) throws PuppetException;

}
