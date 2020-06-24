package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyToWord")
public class MachineStringPropertyCopyToWordEmitter extends AbstractMachineStringPropertyReference
		implements CopyToWordEmitter {

	public MachineStringPropertyCopyToWordEmitter() {}

	@Override
	public void buildArgv(Machine machine, InFile source, String destination, Consumer<String> sink)
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
