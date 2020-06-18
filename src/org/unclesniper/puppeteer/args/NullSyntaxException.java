package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.PuppetException;

public class NullSyntaxException extends PuppetException {

	private final String syntaxProperty;

	public NullSyntaxException(String syntaxProperty) {
		super("Syntax property '" + syntaxProperty + "' was not set");
		this.syntaxProperty = syntaxProperty;
	}

	public String getSyntaxProperty() {
		return syntaxProperty;
	}

}
