package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyExecWord")
public class MachineStringPropertyExecWordEmitter extends AbstractMachineStringPropertyReference
		implements ExecWordEmitter {

	public MachineStringPropertyExecWordEmitter() {}

	@Override
	public void buildArgv(ExecSlave.ExecInfo info, Consumer<String> sink) throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(info.machine);
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
