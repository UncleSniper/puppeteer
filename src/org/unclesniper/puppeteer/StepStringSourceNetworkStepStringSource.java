package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepStringNetworkStepString")
public class StepStringSourceNetworkStepStringSource extends AbstractNetworkStepStringSource {

	private StepStringSource source;

	public StepStringSourceNetworkStepStringSource() {}

	public StepStringSourceNetworkStepStringSource(StepStringSource source) {
		this.source = source;
	}

	public StepStringSource getSource() {
		return source;
	}

	public void setSource(StepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(NetworkStep.NetworkStepInfo info, StringBuilder sink) throws PuppetException {
		if(source != null)
			source.buildString(info, sink);
	}

}
