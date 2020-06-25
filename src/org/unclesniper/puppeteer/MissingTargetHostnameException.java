package org.unclesniper.puppeteer;

public class MissingTargetHostnameException extends PuppetException {

	private final Machine machine;

	public MissingTargetHostnameException(Machine machine) {
		super("Target machine does not specify an address (hostname etc.) that can be used to reach it");
		this.machine = machine;
	}

}
