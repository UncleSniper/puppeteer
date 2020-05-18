package org.unclesniper.puppeteer;

public class StringCopyFromStringSource implements CopyFromStringSource {

	private String string;

	public StringCopyFromStringSource(String string) {
		this.string = string;
	}

	public StringCopyFromStringSource() {}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public void buildString(Machine machine, String source, OutFile destination, StringBuilder sink)
			throws PuppetException {
		if(string != null)
			sink.append(string);
	}

}
