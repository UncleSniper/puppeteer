package org.unclesniper.puppeteer;

import java.util.function.Consumer;

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
	protected void buildArgvImpl(Machine machine, InFile source, String destination, Consumer<String> sink)
			throws PuppetException {
		String path = source.asFile();
		sink.accept(transform == null ? path : transform.transformString(path));
	}

}
