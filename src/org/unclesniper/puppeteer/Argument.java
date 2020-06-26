package org.unclesniper.puppeteer;

public interface Argument extends Variable {

	void initializeValue(ScopeLevel level);

	void addValue(ScopeLevel level, String specifier, String source) throws PuppetException;

}
