package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineIntPropertyMachineStepWord")
public class MachineIntPropertyMachineStepWordEmitter extends AbstractMachineIntPropertyReference
		implements MachineStepWordEmitter {

	public MachineIntPropertyMachineStepWordEmitter() {}

	@Override
	public void buildArgv(MachineStep.MachineStepInfo info, Consumer<String> sink)
			throws MissingMachineIntPropertyException, MissingTargetMachineException, MissingExecHostException {
		try {
			String value = getTransformedPropertyValue(getCorrectMachine(info.getMachine(), null));
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
