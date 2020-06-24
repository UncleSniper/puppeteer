package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("execString")
public class StringExecStringSource extends AbstractExecStringSource {

	private String string;

	public StringExecStringSource() {}

	public StringExecStringSource(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	protected void buildStringImpl(ExecSlave.ExecInfo info, StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
