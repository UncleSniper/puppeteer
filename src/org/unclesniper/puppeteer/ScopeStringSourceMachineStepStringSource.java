package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("scopeStringMachineStepString")
public class ScopeStringSourceMachineStepStringSource extends AbstractMachineStepStringSource {

	private ScopeStringSource source;

	public ScopeStringSourceMachineStepStringSource() {}

	public ScopeStringSourceMachineStepStringSource(ScopeStringSource source) {
		this.source = source;
	}

	public ScopeStringSource getSource() {
		return source;
	}

	public void setSource(ScopeStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(MachineStep.MachineStepInfo info, StringBuilder sink) throws PuppetException {
		if(source != null)
			source.buildString(info.getScope(), sink);
	}

}
