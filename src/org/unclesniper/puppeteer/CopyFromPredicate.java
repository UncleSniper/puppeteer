package org.unclesniper.puppeteer;

public interface CopyFromPredicate extends Traceable, StructPrintable {

	boolean test(CopySlave.CopyFromInfo info) throws PuppetException;

}
