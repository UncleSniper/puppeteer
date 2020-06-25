package org.unclesniper.puppeteer.util;

import org.unclesniper.puppeteer.PuppetException;

public class InvalidTriStateException extends PuppetException {

	private final String specifier;

	private final String source;

	public InvalidTriStateException(String specifier, String source, Throwable cause) {
		super("Invalid TriState" + (source == null ? "" : " given " + source) + ": " + specifier, cause);
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
