package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.function.Consumer;

public class ArgvExecWordEmitter implements ExecWordEmitter {

	private StringTransform transform;

	public ArgvExecWordEmitter() {}

	public StringTransform getTransform() {
		return transform;
	}

	public void setTransform(StringTransform transform) {
		this.transform = transform;
	}

	@Override
	public void buildArgv(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			Consumer<String> sink) throws PuppetException {
		for(String word : argv.asIterable())
			sink.accept(transform == null ? word : transform.transformString(word));
	}

}
