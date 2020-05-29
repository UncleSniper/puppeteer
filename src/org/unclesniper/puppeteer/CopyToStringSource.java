package org.unclesniper.puppeteer;

public interface CopyToStringSource extends Traceable {

	void buildString(Machine machine, InFile source, String destination, StringBuilder sink)
			throws PuppetException;

}
