package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("scopeStringCopyToString")
public class ScopeStringSourceCopyToStringSource extends AbstractCopyToStringSource {

	private ScopeStringSource source;

	public ScopeStringSourceCopyToStringSource() {}

	public ScopeStringSourceCopyToStringSource(ScopeStringSource source) {
		this.source = source;
	}

	public ScopeStringSource getSource() {
		return source;
	}

	public void setSource(ScopeStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(CopySlave.CopyToInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo.getScope(), sink);
	}

}
