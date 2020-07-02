package org.unclesniper.puppeteer;

public interface ScopePredicate extends Traceable, StructPrintable {

	boolean test(ScopeLevel scope) throws PuppetException;

}
