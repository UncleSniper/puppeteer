package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.function.Consumer;

public class MachineStringPropertyExecWordEmitter extends AbstractMachineStringPropertyReference
		implements ExecWordEmitter {

	public MachineStringPropertyExecWordEmitter() {}

	@Override
	public void buildArgv(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			Consumer<String> sink) throws MissingMachineStringPropertyException {
		String value = getPropertyValue(machine);
		if(value != null)
			sink.accept(value);
	}

}
