package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyMachineStepWord")
public class MachineStringPropertyMachineStepWordEmitter extends AbstractMachineStringPropertyReference
		implements MachineStepWordEmitter {

	public MachineStringPropertyMachineStepWordEmitter() {}

	@Override
	public void buildArgv(MachineStep.MachineStepInfo info, Consumer<String> sink)
			throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(info.getMachine());
			if(value != null) {
				putPrefixWords(sink);
				sink.accept(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
