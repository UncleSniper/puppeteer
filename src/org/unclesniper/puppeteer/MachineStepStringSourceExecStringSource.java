package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStepStringExecString")
public class MachineStepStringSourceExecStringSource extends AbstractExecStringSource {

	private MachineStepStringSource source;

	public MachineStepStringSourceExecStringSource() {}

	public MachineStepStringSourceExecStringSource(MachineStepStringSource source) {
		this.source = source;
	}

	public MachineStepStringSource getSource() {
		return source;
	}

	public void setSource(MachineStepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(ExecSlave.ExecInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo, sink);
	}

}
