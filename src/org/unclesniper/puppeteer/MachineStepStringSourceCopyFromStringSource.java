package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStepStringCopyFromString")
public class MachineStepStringSourceCopyFromStringSource extends AbstractCopyFromStringSource {

	private MachineStepStringSource source;

	public MachineStepStringSourceCopyFromStringSource() {}

	public MachineStepStringSourceCopyFromStringSource(MachineStepStringSource source) {
		this.source = source;
	}

	public MachineStepStringSource getSource() {
		return source;
	}

	public void setSource(MachineStepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(CopySlave.CopyFromInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo, sink);
	}

}
