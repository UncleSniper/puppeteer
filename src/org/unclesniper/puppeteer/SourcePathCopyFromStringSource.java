package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("sourcePathCopyFromString")
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
	protected void buildStringImpl(CopySlave.CopyFromInfo info, StringBuilder sink) {
		sink.append(transform == null ? info.source : transform.transformString(info.source));
	}

}
