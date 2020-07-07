package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("scopeStringCopyFromString")
public class ScopeStringSourceCopyFromStringSource extends AbstractCopyFromStringSource {

	private ScopeStringSource source;

	public ScopeStringSourceCopyFromStringSource() {}

	public ScopeStringSourceCopyFromStringSource(ScopeStringSource source) {
		this.source = source;
	}

	public ScopeStringSource getSource() {
		return source;
	}

	public void setSource(ScopeStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(CopySlave.CopyFromInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo.getScope(), sink);
	}

}
