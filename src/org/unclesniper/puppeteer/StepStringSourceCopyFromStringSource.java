package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepStringCopyFromString")
public class StepStringSourceCopyFromStringSource extends AbstractCopyFromStringSource {

	private StepStringSource source;

	public StepStringSourceCopyFromStringSource() {}

	public StepStringSourceCopyFromStringSource(StepStringSource source) {
		this.source = source;
	}

	public StepStringSource getSource() {
		return source;
	}

	public void setSource(StepStringSource source) {
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
