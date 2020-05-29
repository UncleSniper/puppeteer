package org.unclesniper.puppeteer;

import java.util.Map;

public class MachineStringPropertyExecStringSource extends AbstractMachineStringPropertyReference
		implements ExecStringSource {

	public MachineStringPropertyExecStringSource() {}

	@Override
	public void buildString(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			StringBuilder sink) throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(machine);
			if(value != null)
				sink.append(value);
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
