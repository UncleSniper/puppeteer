package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineIntPropertyExecString")
public class MachineIntPropertyExecStringSource extends AbstractMachineIntPropertyReference
		implements ExecStringSource {

	public MachineIntPropertyExecStringSource() {}

	@Override
	public void buildString(ExecSlave.ExecInfo info, StringBuilder sink)
			throws MissingMachineIntPropertyException, MissingTargetMachineException, MissingExecHostException {
		try {
			String value = getTransformedPropertyValue(getCorrectMachine(info.machine, info.execHost));
			if(value != null) {
				putPrefixWords(sink);
				sink.append(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineIntPropertyException | MissingTargetMachineException | MissingExecHostException e) {
			e.addPuppetFrame(this);
			throw e;
		}
	}

}
