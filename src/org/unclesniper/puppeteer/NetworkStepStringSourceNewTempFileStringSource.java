package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkStepStringNewTempFileString")
public class NetworkStepStringSourceNewTempFileStringSource extends AbstractNewTempFileStringSource {

	private NetworkStepStringSource source;

	public NetworkStepStringSourceNewTempFileStringSource() {}

	public NetworkStepStringSourceNewTempFileStringSource(NetworkStepStringSource source) {
		this.source = source;
	}

	public NetworkStepStringSource getSource() {
		return source;
	}

	public void setSource(NetworkStepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(FileSlave.NewTempFileInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo, sink);
	}

}
