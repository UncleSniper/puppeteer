package org.unclesniper.puppeteer;

import java.util.Map;

public class ArgvExecStringSource implements ExecStringSource {

	private String separator = " ";

	private StringTransform transform;

	public ArgvExecStringSource() {}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public StringTransform getTransform() {
		return transform;
	}

	public void setTransform(StringTransform transform) {
		this.transform = transform;
	}

	public void setTransform(WordQuoter transform) {
		this.transform = transform == null ? null : new QuotingStringTransform(transform);
	}

	@Override
	public void buildString(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			StringBuilder sink)  {
		boolean first = true;
		for(String word : argv.asIterable()) {
			if(first)
				first = false;
			else if(separator != null)
				sink.append(separator);
			sink.append(transform == null ? word : transform.transformString(word));
		}
	}

}
