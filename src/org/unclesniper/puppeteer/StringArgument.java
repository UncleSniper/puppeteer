package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stringArg")
public class StringArgument extends StringVariable implements Argument {

	public StringArgument() {}

	public StringArgument(String name) {
		super(name);
	}

	@Override
	public void initializeValue(ScopeLevel level) {
		level.setString(this, null, AssignmentScope.LOCAL);
	}

	@Override
	public void addValue(ScopeLevel level, String specifier) throws PuppetException {
		level.setString(this, specifier, AssignmentScope.LOCAL);
	}

}
