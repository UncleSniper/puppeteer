package org.unclesniper.puppeteer;

public class SourcePathCopyFromStringSource extends AbstractCopyFromStringSource {

	private StringTransform transform;

	public SourcePathCopyFromStringSource() {}

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
	protected void buildStringImpl(Machine machine, String source, OutFile destination, StringBuilder sink) {
		sink.append(transform == null ? source : transform.transformString(source));
	}

}
