package org.unclesniper.puppeteer;

public class MachineStringPropertyCopyToStringSource extends AbstractMachineStringPropertyReference
		implements CopyToStringSource {

	public MachineStringPropertyCopyToStringSource() {}

	@Override
	public void buildString(Machine machine, InFile source, String destination, StringBuilder sink)
			throws MissingMachineStringPropertyException {
		String value = getPropertyValue(machine);
		if(value != null)
			sink.append(value);
	}

}
