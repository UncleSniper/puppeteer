package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyMachineStepString")
public class MachineStringPropertyMachineStepStringSource extends AbstractMachineStringPropertyReference
		implements MachineStepStringSource {

	public MachineStringPropertyMachineStepStringSource() {}

	@Override
	public void buildString(MachineStep.MachineStepInfo info, StringBuilder sink)
			throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(info.getMachine());
			if(value != null)
				sink.append(value);
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
