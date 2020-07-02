package org.unclesniper.puppeteer;

public interface DeleteFilePredicate extends Traceable, StructPrintable {

	boolean test(FileSlave.DeleteFileInfo info) throws PuppetException;

}
