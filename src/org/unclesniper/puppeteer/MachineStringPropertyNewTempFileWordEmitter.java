package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyNewTempFileWord")
public class MachineStringPropertyNewTempFileWordEmitter extends AbstractMachineStringPropertyReference
		implements NewTempFileWordEmitter {

	public MachineStringPropertyNewTempFileWordEmitter() {}

	@Override
	public void buildArgv(Machine machine, Consumer<String> sink) throws MissingMachineStringPropertyException {
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
