package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStepStringCopyToString")
public class MachineStepStringSourceCopyToStringSource extends AbstractCopyToStringSource {

	private MachineStepStringSource source;

	public MachineStepStringSourceCopyToStringSource() {}

	public MachineStepStringSourceCopyToStringSource(MachineStepStringSource source) {
		this.source = source;
	}

	public MachineStepStringSource getSource() {
		return source;
	}

	public void setSource(MachineStepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(CopySlave.CopyToInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo, sink);
	}

}
