package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class DestinationPathCopyToWordEmitter extends AbstractCopyToWordEmitter {

	private StringTransform transform;

	public DestinationPathCopyToWordEmitter() {}

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
	protected void buildArgvImpl(Machine machine, InFile source, String destination, Consumer<String> sink) {
		sink.accept(transform == null ? destination : transform.transformString(destination));
	}

}
