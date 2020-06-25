package org.unclesniper.puppeteer;

public class MissingMachineIntPropertyException extends MissingMachinePropertyException {

	public MissingMachineIntPropertyException(Machine machine, String property) {
		super(machine, "int", property);
	}

}
