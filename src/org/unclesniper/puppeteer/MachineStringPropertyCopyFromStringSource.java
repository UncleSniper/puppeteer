package org.unclesniper.puppeteer;

public class MachineStringPropertyCopyFromStringSource extends AbstractMachineStringPropertyReference
		implements CopyFromStringSource {

	public MachineStringPropertyCopyFromStringSource() {}

	@Override
	public void buildString(Machine machine, String source, OutFile destination, StringBuilder sink)
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
