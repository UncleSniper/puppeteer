package org.unclesniper.puppeteer;

public class InvalidBooleanException extends PuppetException {

	private final String specifier;

	private final String source;

	public InvalidBooleanException(String specifier, String source) {
		super("Invalid Boolean" + (source == null ? "" : " given " + source) + ": " + specifier);
		this.specifier = specifier;
		this.source = source;
	}

	public String getSpecifier() {
		return specifier;
	}

	public String getSource() {
		return source;
	}

}
