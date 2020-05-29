package org.unclesniper.puppeteer;

public interface DeleteFileStringSource extends Traceable {

	void buildString(Machine machine, String file, StringBuilder sink) throws PuppetException;

}
