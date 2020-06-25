package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyDeleteFileString")
public class MachineStringPropertyDeleteFileStringSource extends AbstractMachineStringPropertyReference
		implements DeleteFileStringSource {

	public MachineStringPropertyDeleteFileStringSource() {}

	@Override
	public void buildString(FileSlave.DeleteFileInfo info, StringBuilder sink)
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
