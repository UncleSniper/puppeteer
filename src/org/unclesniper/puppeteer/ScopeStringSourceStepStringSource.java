package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("scopeStringStepString")
public class ScopeStringSourceStepStringSource extends AbstractStepStringSource {

	private ScopeStringSource source;

	public ScopeStringSourceStepStringSource() {}

	public ScopeStringSourceStepStringSource(ScopeStringSource source) {
		this.source = source;
	}

	public ScopeStringSource getSource() {
		return source;
	}

	public void setSource(ScopeStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(Step.StepInfo info, StringBuilder sink) throws PuppetException {
		if(source != null)
			source.buildString(info.getScope(), sink);
	}

}
