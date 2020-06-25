package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineIntPropertyNewTempFileString")
public class MachineIntPropertyNewTempFileStringSource extends AbstractMachineIntPropertyReference
		implements NewTempFileStringSource {

	public MachineIntPropertyNewTempFileStringSource() {}

	@Override
	public void buildString(FileSlave.NewTempFileInfo info, StringBuilder sink)
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
