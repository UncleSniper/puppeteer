package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyCopyToString")
public class MachineStringPropertyCopyToStringSource extends AbstractMachineStringPropertyReference
		implements CopyToStringSource {

	public MachineStringPropertyCopyToStringSource() {}

	@Override
	public void buildString(Machine machine, InFile source, String destination, StringBuilder sink)
			throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(machine);
			if(value != null)
				sink.append(value);
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
