package org.unclesniper.puppeteer;

class MachineStringPropertyDeleteFileStringSource extends AbstractMachineStringPropertyReference
		implements DeleteFileStringSource {

	public MachineStringPropertyDeleteFileStringSource() {}

	@Override
	public void buildString(Machine machine, String file, StringBuilder sink)
			throws MissingMachineStringPropertyException {
		String value = getPropertyValue(machine);
		if(value != null)
			sink.append(value);
	}

}
