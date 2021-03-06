package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("sourcePathCopyToWord")
public class SourcePathCopyToWordEmitter extends AbstractCopyToWordEmitter {

	private StringTransform transform;

	public SourcePathCopyToWordEmitter() {}

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
	protected void buildArgvImpl(CopySlave.CopyToInfo info, Consumer<String> sink)
			throws PuppetException {
		String path = info.source.asFile();
		sink.accept(transform == null ? path : transform.transformString(path));
	}

}
