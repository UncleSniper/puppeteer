package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("copyFromString")
public class StringCopyFromStringSource extends AbstractCopyFromStringSource {

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
	protected void buildStringImpl(Machine machine, String source, OutFile destination, StringBuilder sink)
			throws PuppetException {
		if(string != null)
			sink.append(string);
	}

}
