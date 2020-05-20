package org.unclesniper.puppeteer;

public class DestinationPathCopyFromStringSource implements CopyFromStringSource {

	private StringTransform transform;

	public DestinationPathCopyFromStringSource() {}

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
	public void buildString(Machine machine, String source, OutFile destination, StringBuilder sink)
			throws PuppetException {
		String path = destination.asFile();
		sink.append(transform == null ? path : transform.transformString(path));
	}

}
