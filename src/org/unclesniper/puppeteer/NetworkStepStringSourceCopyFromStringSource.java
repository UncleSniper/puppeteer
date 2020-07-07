package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkStepStringCopyFromString")
public class NetworkStepStringSourceCopyFromStringSource extends AbstractCopyFromStringSource {

	private NetworkStepStringSource source;

	public NetworkStepStringSourceCopyFromStringSource() {}

	public NetworkStepStringSourceCopyFromStringSource(NetworkStepStringSource source) {
		this.source = source;
	}

	public NetworkStepStringSource getSource() {
		return source;
	}

	public void setSource(NetworkStepStringSource source) {
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
