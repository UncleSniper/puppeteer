package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyFromWord")
public class MachineStringPropertyCopyFromWordEmitter extends AbstractMachineStringPropertyReference
		implements CopyFromWordEmitter {

	public MachineStringPropertyCopyFromWordEmitter() {}

	@Override
	public void buildArgv(CopySlave.CopyFromInfo info, Consumer<String> sink)
			throws MissingMachineStringPropertyException, MissingTargetMachineException, MissingExecHostException {
		try {
			String value = getPropertyValue(getCorrectMachine(info.machine, info.execHost));
			if(value != null) {
				putPrefixWords(sink);
				sink.accept(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineStringPropertyException | MissingTargetMachineException
				| MissingExecHostException e) {
			e.addPuppetFrame(this);
			throw e;
		}
	}

}
