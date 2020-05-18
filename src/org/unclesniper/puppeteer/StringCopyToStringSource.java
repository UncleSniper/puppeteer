package org.unclesniper.puppeteer;

public class StringCopyToStringSource implements CopyToStringSource {

	private String string;

	public StringCopyToStringSource() {}

	public StringCopyToStringSource(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public void buildString(Machine machine, InFile source, String destination, StringBuilder sink)
			throws PuppetException {
		if(string != null)
			sink.append(string);
	}

}
