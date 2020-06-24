package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("destinationPathCopyFromString")
public class DestinationPathCopyFromStringSource extends AbstractCopyFromStringSource {

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
	protected void buildStringImpl(CopySlave.CopyFromInfo info, StringBuilder sink) throws PuppetException {
		String path = info.destination.asFile();
		sink.append(transform == null ? path : transform.transformString(path));
	}

}
