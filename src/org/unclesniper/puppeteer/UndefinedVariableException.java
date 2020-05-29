package org.unclesniper.puppeteer;

public abstract class UndefinedVariableException extends PuppetException {

	public UndefinedVariableException(String message) {
		super(message);
	}

	public abstract Variable getVariable();

}
