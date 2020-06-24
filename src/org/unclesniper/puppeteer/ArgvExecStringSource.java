package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("argvExecString")
public class ArgvExecStringSource extends AbstractExecStringSource {

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
	protected void buildStringImpl(ExecSlave.ExecInfo info, StringBuilder sink)  {
		boolean first = true;
		for(String word : info.argv.asIterable()) {
			if(first)
				first = false;
			else if(separator != null)
				sink.append(separator);
			sink.append(transform == null ? word : transform.transformString(word));
		}
	}

}
