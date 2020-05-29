package org.unclesniper.puppeteer;

import java.util.function.Consumer;

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
	protected void buildArgvImpl(Machine machine, String source, OutFile destination, Consumer<String> sink) {
		sink.accept(transform == null ? source : transform.transformString(source));
	}

}
