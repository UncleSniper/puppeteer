package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("destinationPathCopyToString")
public class DestinationPathCopyToStringSource extends AbstractCopyToStringSource {

	private StringTransform transform;

	public DestinationPathCopyToStringSource() {}

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
	protected void buildStringImpl(Machine machine, InFile source, String destination, StringBuilder sink) {
		sink.append(transform == null ? destination : transform.transformString(destination));
	}

}
