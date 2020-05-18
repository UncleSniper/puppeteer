package org.unclesniper.puppeteer;

public interface CopyFromStringSource {

	void buildString(Machine machine, String source, OutFile destination, StringBuilder sink)
			throws PuppetException;

}
