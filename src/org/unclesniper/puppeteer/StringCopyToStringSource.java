package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("copyToString")
public class StringCopyToStringSource extends AbstractCopyToStringSource {

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
	protected void buildStringImpl(CopySlave.CopyToInfo info, StringBuilder sink)
			throws PuppetException {
		if(string != null)
			sink.append(string);
	}

}
