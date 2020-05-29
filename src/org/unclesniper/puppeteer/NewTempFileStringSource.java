package org.unclesniper.puppeteer;

public interface NewTempFileStringSource extends Traceable {

	void buildString(Machine machine, StringBuilder sink) throws PuppetException;

}
