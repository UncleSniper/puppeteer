package org.unclesniper.puppeteer;

public class MissingMachineStringPropertyException extends MissingMachinePropertyException {

	public MissingMachineStringPropertyException(Machine machine, String property) {
		super(machine, "string", property);
	}

}
