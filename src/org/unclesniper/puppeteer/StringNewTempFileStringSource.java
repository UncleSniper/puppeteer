package org.unclesniper.puppeteer;

public class StringNewTempFileStringSource extends AbstractNewTempFileStringSource {

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
	protected void buildStringImpl(Machine machine, StringBuilder sink) throws PuppetException {
		if(string != null)
			sink.append(string);
	}

}
