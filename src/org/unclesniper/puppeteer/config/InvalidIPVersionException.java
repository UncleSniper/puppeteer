package org.unclesniper.puppeteer.config;

import org.unclesniper.puppeteer.PuppetException;

public class InvalidIPVersionException extends PuppetException {

	private final String specifier;

	private final String source;

	public InvalidIPVersionException(String specifier, String source, Throwable cause) {
		super("Invalid IPVersion" + (source == null ? "" : " given " + source) + ": " + specifier, cause);
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
