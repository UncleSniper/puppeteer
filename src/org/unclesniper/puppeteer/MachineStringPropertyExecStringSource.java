package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyExecString")
public class MachineStringPropertyExecStringSource extends AbstractMachineStringPropertyReference
		implements ExecStringSource {

	public MachineStringPropertyExecStringSource() {}

	@Override
	public void buildString(ExecSlave.ExecInfo info, StringBuilder sink)
			throws MissingMachineStringPropertyException, MissingTargetMachineException, MissingExecHostException {
		try {
			String value = getPropertyValue(getCorrectMachine(info.machine, info.execHost));
			if(value != null) {
				putPrefixWords(sink);
				sink.append(value);
				putSuffixWords(sink);
			}
		}
		catch(MissingMachineStringPropertyException mmspe) {
			mmspe.addPuppetFrame(this);
			throw mmspe;
		}
	}

}
