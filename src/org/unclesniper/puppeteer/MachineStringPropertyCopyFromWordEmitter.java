package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class MachineStringPropertyCopyFromWordEmitter extends AbstractMachineStringPropertyReference
		implements CopyFromWordEmitter {

	public MachineStringPropertyCopyFromWordEmitter() {}

	@Override
	public void buildArgv(Machine machine, String source, OutFile destination, Consumer<String> sink)
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
