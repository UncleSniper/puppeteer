package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("scopeStringDeleteFileString")
public class ScopeStringSourceDeleteFileStringSource extends AbstractDeleteFileStringSource {

	private ScopeStringSource source;

	public ScopeStringSourceDeleteFileStringSource() {}

	public ScopeStringSourceDeleteFileStringSource(ScopeStringSource source) {
		this.source = source;
	}

	public ScopeStringSource getSource() {
		return source;
	}

	public void setSource(ScopeStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(FileSlave.DeleteFileInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo.getScope(), sink);
	}

}
