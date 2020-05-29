package org.unclesniper.puppeteer;

public interface Argument extends Variable {

	void initializeValue(ScopeLevel level);

	void addValue(ScopeLevel level, String specifier) throws PuppetException;

}
