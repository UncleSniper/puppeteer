package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class MachineStringPropertyCopyToWordEmitter extends AbstractMachineStringPropertyReference
		implements CopyToWordEmitter {

	public MachineStringPropertyCopyToWordEmitter() {}

	@Override
	public void buildArgv(Machine machine, InFile source, String destination, Consumer<String> sink)
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
