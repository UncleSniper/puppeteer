package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class DestinationPathCopyToWordEmitter implements CopyToWordEmitter {

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
	public void buildArgv(Machine machine, InFile source, String destination, Consumer<String> sink) {
		sink.accept(transform == null ? destination : transform.transformString(destination));
	}

}
