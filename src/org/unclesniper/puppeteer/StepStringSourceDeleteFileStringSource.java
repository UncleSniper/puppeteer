package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepStringDeleteFileString")
public class StepStringSourceDeleteFileStringSource extends AbstractDeleteFileStringSource {

	private StepStringSource source;

	public StepStringSourceDeleteFileStringSource() {}

	public StepStringSourceDeleteFileStringSource(StepStringSource source) {
		this.source = source;
	}

	public StepStringSource getSource() {
		return source;
	}

	public void setSource(StepStringSource source) {
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
