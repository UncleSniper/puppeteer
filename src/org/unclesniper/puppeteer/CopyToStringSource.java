package org.unclesniper.puppeteer;

public interface CopyToStringSource {

	void buildString(Machine machine, InFile source, String destination, StringBuilder sink)
			throws PuppetException;

}
