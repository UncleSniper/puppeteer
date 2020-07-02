package org.unclesniper.puppeteer;

public interface NewTempFilePredicate extends Traceable, StructPrintable {

	boolean test(FileSlave.NewTempFileInfo info) throws PuppetException;

}
