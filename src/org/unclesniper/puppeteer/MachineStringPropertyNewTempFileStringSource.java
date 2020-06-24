package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyNewTempFileString")
public class MachineStringPropertyNewTempFileStringSource extends AbstractMachineStringPropertyReference
		implements NewTempFileStringSource {

	public MachineStringPropertyNewTempFileStringSource() {}

	@Override
	public void buildString(Machine machine, StringBuilder sink) throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(machine);
			if(value != null) {
				putPrefixWords(sink);
				sink.append(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
