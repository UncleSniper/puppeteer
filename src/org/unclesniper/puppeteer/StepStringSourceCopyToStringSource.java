package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepStringCopyToString")
public class StepStringSourceCopyToStringSource extends AbstractCopyToStringSource {

	private StepStringSource source;

	public StepStringSourceCopyToStringSource() {}

	public StepStringSourceCopyToStringSource(StepStringSource source) {
		this.source = source;
	}

	public StepStringSource getSource() {
		return source;
	}

	public void setSource(StepStringSource source) {
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
