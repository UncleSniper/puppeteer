package org.unclesniper.puppeteer;

public class MachineStringPropertyNewTempFileStringSource extends AbstractMachineStringPropertyReference
		implements NewTempFileStringSource {

	public MachineStringPropertyNewTempFileStringSource() {}

	@Override
	public void buildString(Machine machine, StringBuilder sink) throws MissingMachineStringPropertyException {
		String value = getPropertyValue(machine);
		if(value != null)
			sink.append(value);
	}

}
