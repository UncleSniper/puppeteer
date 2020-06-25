package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyToWord")
public class MachineStringPropertyCopyToWordEmitter extends AbstractMachineStringPropertyReference
		implements CopyToWordEmitter {

	public MachineStringPropertyCopyToWordEmitter() {}

	@Override
	public void buildArgv(CopySlave.CopyToInfo info, Consumer<String> sink)
			throws MissingMachineStringPropertyException, MissingTargetMachineException, MissingExecHostException {
		try {
			String value = getPropertyValue(getCorrectMachine(info.machine, info.execHost));
			if(value != null) {
				putPrefixWords(sink);
				sink.accept(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineStringPropertyException | MissingTargetMachineException | MissingExecHostException e) {
			e.addPuppetFrame(this);
			throw e;
		}
	}

}
