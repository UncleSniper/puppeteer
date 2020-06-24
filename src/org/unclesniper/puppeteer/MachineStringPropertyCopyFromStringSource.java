package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyFromString")
public class MachineStringPropertyCopyFromStringSource extends AbstractMachineStringPropertyReference
		implements CopyFromStringSource {

	public MachineStringPropertyCopyFromStringSource() {}

	@Override
	public void buildString(CopySlave.CopyFromInfo info, StringBuilder sink)
			throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(info.machine);
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
