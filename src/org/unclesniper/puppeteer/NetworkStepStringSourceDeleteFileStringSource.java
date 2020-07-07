package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkStepStringDeleteFileString")
public class NetworkStepStringSourceDeleteFileStringSource extends AbstractDeleteFileStringSource {

	private NetworkStepStringSource source;

	public NetworkStepStringSourceDeleteFileStringSource() {}

	public NetworkStepStringSourceDeleteFileStringSource(NetworkStepStringSource source) {
		this.source = source;
	}

	public NetworkStepStringSource getSource() {
		return source;
	}

	public void setSource(NetworkStepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(FileSlave.DeleteFileInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo, sink);
	}

}
