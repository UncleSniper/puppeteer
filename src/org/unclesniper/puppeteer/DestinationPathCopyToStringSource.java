package org.unclesniper.puppeteer;

public class DestinationPathCopyToStringSource implements CopyToStringSource {

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
	public void buildString(Machine machine, InFile source, String destination, StringBuilder sink) {
		sink.append(transform == null ? destination : transform.transformString(destination));
	}

}
