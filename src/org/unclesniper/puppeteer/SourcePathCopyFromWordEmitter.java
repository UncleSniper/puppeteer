package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class SourcePathCopyFromWordEmitter implements CopyFromWordEmitter {

	private StringTransform transform;

	public SourcePathCopyFromWordEmitter() {}

	public StringTransform getTransform() {
		return transform;
	}

	public void setTransform(StringTransform transform) {
		this.transform = transform;
	}

	@Override
	public void buildArgv(Machine machine, String source, OutFile destination, Consumer<String> sink) {
		sink.accept(transform == null ? source : transform.transformString(source));
	}

}
