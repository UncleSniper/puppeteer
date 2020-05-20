package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class PathDeleteFileWordEmitter implements DeleteFileWordEmitter {

	private StringTransform transform;

	public PathDeleteFileWordEmitter() {}

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
	public void buildArgv(Machine machine, String file, Consumer<String> sink) {
		sink.accept(transform == null ? file : transform.transformString(file));
	}

}
