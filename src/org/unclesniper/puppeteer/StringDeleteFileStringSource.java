package org.unclesniper.puppeteer;

public class StringDeleteFileStringSource implements DeleteFileStringSource {

	private String string;

	public StringDeleteFileStringSource() {}

	public StringDeleteFileStringSource(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public void buildString(Machine machine, String file, StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
