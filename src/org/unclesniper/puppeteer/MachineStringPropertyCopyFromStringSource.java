package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyFromString")
public class MachineStringPropertyCopyFromStringSource extends AbstractMachineStringPropertyReference
		implements CopyFromStringSource {

	public MachineStringPropertyCopyFromStringSource() {}

	@Override
	public void buildString(Machine machine, String source, OutFile destination, StringBuilder sink)
			throws MissingMachineStringPropertyException {
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
