package org.unclesniper.puppeteer;

import java.util.Map;

public class StringExecStringSource implements ExecStringSource {

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
	public void buildString(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
