package org.unclesniper.puppeteer;

public class StringNetworkStepStringSource implements NetworkStepStringSource {

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
	public void buildString(NetworkStep.NetworkStepInfo info, StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
