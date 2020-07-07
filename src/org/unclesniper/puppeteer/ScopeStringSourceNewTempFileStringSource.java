package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("scopeStringNewTempFileString")
public class ScopeStringSourceNewTempFileStringSource extends AbstractNewTempFileStringSource {

	private ScopeStringSource source;

	public ScopeStringSourceNewTempFileStringSource() {}

	public ScopeStringSourceNewTempFileStringSource(ScopeStringSource source) {
		this.source = source;
	}

	public ScopeStringSource getSource() {
		return source;
	}

	public void setSource(ScopeStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(FileSlave.NewTempFileInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo.getScope(), sink);
	}

}
