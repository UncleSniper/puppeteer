package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class MachineStringPropertyNewTempFileWordEmitter extends AbstractMachineStringPropertyReference
		implements NewTempFileWordEmitter {

	public MachineStringPropertyNewTempFileWordEmitter() {}

	@Override
	public void buildArgv(Machine machine, Consumer<String> sink) throws MissingMachineStringPropertyException {
		String value = getPropertyValue(machine);
		if(value != null)
			sink.accept(value);
	}

}
