package org.unclesniper.puppeteer;

public class StringDeleteFileStringSource extends AbstractDeleteFileStringSource {

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
	protected void buildStringImpl(Machine machine, String file, StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
