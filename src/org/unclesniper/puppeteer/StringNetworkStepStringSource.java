package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkStepString")
public class StringNetworkStepStringSource extends AbstractNetworkStepStringSource {

	private String string;

	public StringNetworkStepStringSource() {}

	public StringNetworkStepStringSource(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	protected void buildStringImpl(NetworkStep.NetworkStepInfo info, StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
