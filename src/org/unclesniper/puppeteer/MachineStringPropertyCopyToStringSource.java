package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyToString")
public class MachineStringPropertyCopyToStringSource extends AbstractMachineStringPropertyReference
		implements CopyToStringSource {

	public MachineStringPropertyCopyToStringSource() {}

	@Override
	public void buildString(CopySlave.CopyToInfo info, StringBuilder sink)
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
