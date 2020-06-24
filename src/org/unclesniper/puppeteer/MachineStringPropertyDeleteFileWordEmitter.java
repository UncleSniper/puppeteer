package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyDeleteFileWord")
public class MachineStringPropertyDeleteFileWordEmitter extends AbstractMachineStringPropertyReference
		implements DeleteFileWordEmitter {

	public MachineStringPropertyDeleteFileWordEmitter() {}

	@Override
	public void buildArgv(FileSlave.DeleteFileInfo info, Consumer<String> sink)
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
