package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyFromWord")
public class MachineStringPropertyCopyFromWordEmitter extends AbstractMachineStringPropertyReference
		implements CopyFromWordEmitter {

	public MachineStringPropertyCopyFromWordEmitter() {}

	@Override
	public void buildArgv(CopySlave.CopyFromInfo info, Consumer<String> sink)
			throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(info.machine);
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
