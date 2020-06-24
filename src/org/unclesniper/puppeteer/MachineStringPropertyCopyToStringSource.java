package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyToString")
public class MachineStringPropertyCopyToStringSource extends AbstractMachineStringPropertyReference
		implements CopyToStringSource {

	public MachineStringPropertyCopyToStringSource() {}

	@Override
	public void buildString(CopySlave.CopyToInfo info, StringBuilder sink)
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
