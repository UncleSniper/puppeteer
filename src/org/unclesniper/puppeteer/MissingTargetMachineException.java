package org.unclesniper.puppeteer;

public class MissingTargetMachineException extends PuppetException {

	public MissingTargetMachineException() {
		super("Cannot access target Machine as none is present");
	}

}
