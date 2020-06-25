package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyFromString")
public class MachineStringPropertyCopyFromStringSource extends AbstractMachineStringPropertyReference
		implements CopyFromStringSource {

	public MachineStringPropertyCopyFromStringSource() {}

	@Override
	public void buildString(CopySlave.CopyFromInfo info, StringBuilder sink)
			throws MissingMachineStringPropertyException, MissingTargetMachineException, MissingExecHostException {
		try {
			String value = getPropertyValue(getCorrectMachine(info.machine, info.execHost));
			if(value != null) {
				putPrefixWords(sink);
				sink.append(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineStringPropertyException | MissingTargetMachineException | MissingExecHostException e) {
			e.addPuppetFrame(this);
			throw e;
		}
	}

}
