package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("deleteFileString")
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
	protected void buildStringImpl(FileSlave.DeleteFileInfo info, StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
