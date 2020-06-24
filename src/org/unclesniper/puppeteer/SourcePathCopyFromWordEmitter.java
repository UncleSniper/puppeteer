package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("sourcePathCopyFromWord")
public class SourcePathCopyFromWordEmitter extends AbstractCopyFromWordEmitter {

	private StringTransform transform;

	public SourcePathCopyFromWordEmitter() {}

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
	protected void buildArgvImpl(CopySlave.CopyFromInfo info, Consumer<String> sink) {
		sink.accept(transform == null ? info.source : transform.transformString(info.source));
	}

}
