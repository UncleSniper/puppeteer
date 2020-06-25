package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyNewTempFileString")
public class MachineStringPropertyNewTempFileStringSource extends AbstractMachineStringPropertyReference
		implements NewTempFileStringSource {

	public MachineStringPropertyNewTempFileStringSource() {}

	@Override
	public void buildString(FileSlave.NewTempFileInfo info, StringBuilder sink)
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
