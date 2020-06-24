package org.unclesniper.puppeteer;

public interface DeleteFileStringSource extends Traceable {

	void buildString(FileSlave.DeleteFileInfo info, StringBuilder sink) throws PuppetException;

}
