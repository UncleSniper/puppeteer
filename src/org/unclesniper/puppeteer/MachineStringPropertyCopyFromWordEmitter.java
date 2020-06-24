package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyFromWord")
public class MachineStringPropertyCopyFromWordEmitter extends AbstractMachineStringPropertyReference
		implements CopyFromWordEmitter {

	public MachineStringPropertyCopyFromWordEmitter() {}

	@Override
	public void buildArgv(Machine machine, String source, OutFile destination, Consumer<String> sink)
			throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(machine);
			if(value != null) {
				putPrefixWords(sink);
				sink.accept(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
