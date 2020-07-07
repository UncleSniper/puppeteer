package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepStringNewTempFileString")
public class StepStringSourceNewTempFileStringSource extends AbstractNewTempFileStringSource {

	private StepStringSource source;

	public StepStringSourceNewTempFileStringSource() {}

	public StepStringSourceNewTempFileStringSource(StepStringSource source) {
		this.source = source;
	}

	public StepStringSource getSource() {
		return source;
	}

	public void setSource(StepStringSource source) {
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
