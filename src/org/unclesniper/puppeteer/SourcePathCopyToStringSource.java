package org.unclesniper.puppeteer;

public class SourcePathCopyToStringSource implements CopyToStringSource {

	private StringTransform transform;

	public SourcePathCopyToStringSource() {}

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
	public void buildString(Machine machine, InFile source, String destination, StringBuilder sink)
			throws PuppetException {
		String path = source.asFile();
		sink.append(transform == null ? path : transform.transformString(path));
	}

}
