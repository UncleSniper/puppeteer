package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("scopeStringNetworkStepString")
public class ScopeStringSourceNetworkStepStringSource extends AbstractNetworkStepStringSource {

	private ScopeStringSource source;

	public ScopeStringSourceNetworkStepStringSource() {}

	public ScopeStringSourceNetworkStepStringSource(ScopeStringSource source) {
		this.source = source;
	}

	public ScopeStringSource getSource() {
		return source;
	}

	public void setSource(ScopeStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(NetworkStep.NetworkStepInfo info, StringBuilder sink) throws PuppetException {
		if(source != null)
			source.buildString(info.getScope(), sink);
	}

}
