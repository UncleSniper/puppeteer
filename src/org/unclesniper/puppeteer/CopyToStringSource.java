package org.unclesniper.puppeteer;

public interface CopyToStringSource extends Traceable {

	void buildString(CopySlave.CopyToInfo info, StringBuilder sink) throws PuppetException;

}
