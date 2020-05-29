package org.unclesniper.puppeteer;

public interface CopyFromStringSource extends Traceable {

	void buildString(Machine machine, String source, OutFile destination, StringBuilder sink)
			throws PuppetException;

}
