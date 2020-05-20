package org.unclesniper.puppeteer;

public class StringNewTempFileStringSource implements NewTempFileStringSource {

	private String string;

	public StringNewTempFileStringSource() {}

	public StringNewTempFileStringSource(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public void buildString(Machine machine, StringBuilder sink) throws PuppetException {
		if(string != null)
			sink.append(string);
	}

}
