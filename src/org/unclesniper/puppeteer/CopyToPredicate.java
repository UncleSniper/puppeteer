package org.unclesniper.puppeteer;

public interface CopyToPredicate extends Traceable, StructPrintable {

	boolean test(CopySlave.CopyToInfo info) throws PuppetException;

}
