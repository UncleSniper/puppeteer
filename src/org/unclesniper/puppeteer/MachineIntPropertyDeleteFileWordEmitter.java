package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineIntPropertyDeleteFileWord")
public class MachineIntPropertyDeleteFileWordEmitter extends AbstractMachineIntPropertyReference
		implements DeleteFileWordEmitter {

	public MachineIntPropertyDeleteFileWordEmitter() {}

	@Override
	public void buildArgv(FileSlave.DeleteFileInfo info, Consumer<String> sink)
			throws MissingMachineIntPropertyException, MissingTargetMachineException, MissingExecHostException {
		try {
			String value = getTransformedPropertyValue(getCorrectMachine(info.machine, info.execHost));
			if(value != null) {
				putPrefixWords(sink);
				sink.accept(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineIntPropertyException | MissingTargetMachineException | MissingExecHostException e) {
			e.addPuppetFrame(this);
			throw e;
		}
	}

}
