package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyDeleteFileString")
class MachineStringPropertyDeleteFileStringSource extends AbstractMachineStringPropertyReference
		implements DeleteFileStringSource {

	public MachineStringPropertyDeleteFileStringSource() {}

	@Override
	public void buildString(FileSlave.DeleteFileInfo info, StringBuilder sink)
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
