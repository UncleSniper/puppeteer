package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepStringExecString")
public class StepStringSourceExecStringSource extends AbstractExecStringSource {

	private StepStringSource source;

	public StepStringSourceExecStringSource() {}

	public StepStringSourceExecStringSource(StepStringSource source) {
		this.source = source;
	}

	public StepStringSource getSource() {
		return source;
	}

	public void setSource(StepStringSource source) {
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
