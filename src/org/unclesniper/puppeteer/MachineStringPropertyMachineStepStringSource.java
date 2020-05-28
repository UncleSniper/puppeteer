package org.unclesniper.puppeteer;

public class MachineStringPropertyMachineStepStringSource extends AbstractMachineStringPropertyReference
		implements MachineStepStringSource {

	public MachineStringPropertyMachineStepStringSource() {}

	@Override
	public void buildString(MachineStep.MachineStepInfo info, StringBuilder sink)
			throws MissingMachineStringPropertyException {
		String value = getPropertyValue(info.getMachine());
		if(value != null)
			sink.append(value);
	}

}
