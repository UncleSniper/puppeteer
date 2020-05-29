package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class MachineStringPropertyMachineStepWordEmitter extends AbstractMachineStringPropertyReference
		implements MachineStepWordEmitter {

	public MachineStringPropertyMachineStepWordEmitter() {}

	@Override
	public void buildArgv(MachineStep.MachineStepInfo info, Consumer<String> sink)
			throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(info.getMachine());
			if(value != null)
				sink.accept(value);
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
