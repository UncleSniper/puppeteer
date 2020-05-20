package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public class DestinationPathCopyFromWordEmitter implements CopyFromWordEmitter {

	private StringTransform transform;

	public DestinationPathCopyFromWordEmitter() {}

	public StringTransform getTransform() {
		return transform;
	}

	public void setTransform(StringTransform transform) {
		this.transform = transform;
	}

	@Override
	public void buildArgv(Machine machine, String source, OutFile destination, Consumer<String> sink)
			throws PuppetException {
		String path = destination.asFile();
		sink.accept(transform == null ? path : transform.transformString(path));
	}

}
