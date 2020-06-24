package org.unclesniper.puppeteer;

public interface CopyFromStringSource extends Traceable {

	void buildString(CopySlave.CopyFromInfo info, StringBuilder sink) throws PuppetException;

}
