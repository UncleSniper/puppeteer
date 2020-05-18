package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class SourcePathCopyToWordEmitter implements CopyToWordEmitter {

	private StringTransform transform;

	public SourcePathCopyToWordEmitter() {}

	public StringTransform getTransform() {
		return transform;
	}

	public void setTransform(StringTransform transform) {
		this.transform = transform;
	}

	@Override
	public void buildArgv(Machine machine, InFile source, String destination, Consumer<String> sink)
			throws PuppetException {
		String path = source.asFile().getAbsolutePath();
		sink.accept(transform == null ? path : transform.transformString(path));
	}

}
