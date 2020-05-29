package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class MachineStringPropertyDeleteFileWordEmitter extends AbstractMachineStringPropertyReference
		implements DeleteFileWordEmitter {

	public MachineStringPropertyDeleteFileWordEmitter() {}

	@Override
	public void buildArgv(Machine machine, String file, Consumer<String> sink)
			throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(machine);
			if(value != null)
				sink.accept(value);
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
