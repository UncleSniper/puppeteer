package org.unclesniper.puppeteer;

public interface NewTempFileStringSource extends Traceable {

	void buildString(FileSlave.NewTempFileInfo info, StringBuilder sink) throws PuppetException;

}
