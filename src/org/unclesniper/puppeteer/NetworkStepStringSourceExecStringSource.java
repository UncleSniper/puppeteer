package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkStepStringExecString")
public class NetworkStepStringSourceExecStringSource extends AbstractExecStringSource {

	private NetworkStepStringSource source;

	public NetworkStepStringSourceExecStringSource() {}

	public NetworkStepStringSourceExecStringSource(NetworkStepStringSource source) {
		this.source = source;
	}

	public NetworkStepStringSource getSource() {
		return source;
	}

	public void setSource(NetworkStepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(ExecSlave.ExecInfo info, StringBuilder sink) throws PuppetException {
		if(source == null)
			return;
		if(info.stepInfo == null)
			throw new MissingStepInfoException();
		source.buildString(info.stepInfo, sink);
	}

}
