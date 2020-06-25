package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stringArg")
public class StringArgument extends StringVariable implements Argument {

	private String initialValue;

	public StringArgument() {}

	public StringArgument(String name) {
		super(name);
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	@Override
	public void initializeValue(ScopeLevel level) {
		level.setString(this, initialValue, AssignmentScope.LOCAL);
	}

	@Override
	public void addValue(ScopeLevel level, String specifier) throws PuppetException {
		level.setString(this, specifier, AssignmentScope.LOCAL);
	}

}
