package org.unclesniper.puppeteer;

import java.util.Map;
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
	protected void buildStringImpl(Machine machine, Argv argv, String workdir, Map<String, String> environ,
			int flags, StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
