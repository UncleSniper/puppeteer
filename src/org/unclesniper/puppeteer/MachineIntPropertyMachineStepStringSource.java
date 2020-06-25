package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineIntPropertyMachineStepString")
public class MachineIntPropertyMachineStepStringSource extends AbstractMachineIntPropertyReference
		implements MachineStepStringSource {

	public MachineIntPropertyMachineStepStringSource() {}

	@Override
	public void buildString(MachineStep.MachineStepInfo info, StringBuilder sink)
			throws MissingMachineIntPropertyException, MissingTargetMachineException, MissingExecHostException {
		try {
			String value = getTransformedPropertyValue(getCorrectMachine(info.getMachine(), null));
			if(value != null) {
				putPrefixWords(sink);
				sink.append(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineIntPropertyException | MissingTargetMachineException | MissingExecHostException e) {
			e.addPuppetFrame(this);
			throw e;
		}
	}

}
