package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyExecWord")
public class MachineStringPropertyExecWordEmitter extends AbstractMachineStringPropertyReference
		implements ExecWordEmitter {

	public MachineStringPropertyExecWordEmitter() {}

	@Override
	public void buildArgv(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			Consumer<String> sink) throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(machine);
			if(value != null)
				sink.accept(value);
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
