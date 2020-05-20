package org.unclesniper.puppeteer;

public class PathDeleteFileStringSource implements DeleteFileStringSource {

	private StringTransform transform;

	public PathDeleteFileStringSource() {}

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
	public void buildString(Machine machine, String file, StringBuilder sink) {
		sink.append(transform == null ? file : transform.transformString(file));
	}

}
