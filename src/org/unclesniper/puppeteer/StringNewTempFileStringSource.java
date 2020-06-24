package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("newTempFileString")
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
	protected void buildStringImpl(FileSlave.NewTempFileInfo info, StringBuilder sink) throws PuppetException {
		if(string != null)
			sink.append(string);
	}

}
