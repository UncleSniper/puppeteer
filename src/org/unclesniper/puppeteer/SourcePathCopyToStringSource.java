package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("sourcePathCopyToString")
public class SourcePathCopyToStringSource extends AbstractCopyToStringSource {

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
	protected void buildStringImpl(CopySlave.CopyToInfo info, StringBuilder sink)
			throws PuppetException {
		String path = info.source.asFile();
		sink.append(transform == null ? path : transform.transformString(path));
	}

}
