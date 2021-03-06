package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("pathDeleteFileString")
public class PathDeleteFileStringSource extends AbstractDeleteFileStringSource {

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
	protected void buildStringImpl(FileSlave.DeleteFileInfo info, StringBuilder sink) {
		sink.append(transform == null ? info.file : transform.transformString(info.file));
	}

}
