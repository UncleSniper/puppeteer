package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStepStringNewTempFileString")
public class MachineStepStringSourceNewTempFileStringSource extends AbstractNewTempFileStringSource {

	private MachineStepStringSource source;

	public MachineStepStringSourceNewTempFileStringSource() {}

	public MachineStepStringSourceNewTempFileStringSource(MachineStepStringSource source) {
		this.source = source;
	}

	public MachineStepStringSource getSource() {
		return source;
	}

	public void setSource(MachineStepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(FileSlave.NewTempFileInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo, sink);
	}

}
