package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStepStringDeleteFileString")
public class MachineStepStringSourceDeleteFileStringSource extends AbstractDeleteFileStringSource {

	private MachineStepStringSource source;

	public MachineStepStringSourceDeleteFileStringSource() {}

	public MachineStepStringSourceDeleteFileStringSource(MachineStepStringSource source) {
		this.source = source;
	}

	public MachineStepStringSource getSource() {
		return source;
	}

	public void setSource(MachineStepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(FileSlave.DeleteFileInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo, sink);
	}

}
