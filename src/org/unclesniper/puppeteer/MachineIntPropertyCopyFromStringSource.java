package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineIntPropertyCopyFromString")
public class MachineIntPropertyCopyFromStringSource extends AbstractMachineIntPropertyReference
		implements CopyFromStringSource {

	public MachineIntPropertyCopyFromStringSource() {}

	@Override
	public void buildString(CopySlave.CopyFromInfo info, StringBuilder sink)
			throws MissingMachineIntPropertyException, MissingTargetMachineException, MissingExecHostException {
		try {
			String value = getTransformedPropertyValue(getCorrectMachine(info.machine, info.execHost));
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
