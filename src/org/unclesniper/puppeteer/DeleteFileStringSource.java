package org.unclesniper.puppeteer;

public interface DeleteFileStringSource {

	void buildString(Machine machine, String file, StringBuilder sink) throws PuppetException;

}
