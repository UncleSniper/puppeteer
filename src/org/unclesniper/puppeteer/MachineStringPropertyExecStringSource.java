package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStringPropertyExecString")
public class MachineStringPropertyExecStringSource extends AbstractMachineStringPropertyReference
		implements ExecStringSource {

	public MachineStringPropertyExecStringSource() {}

	@Override
	public void buildString(ExecSlave.ExecInfo info, StringBuilder sink)
			throws MissingMachineStringPropertyException {
		try {
			String value = getPropertyValue(info.machine);
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
