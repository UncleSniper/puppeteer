package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("argvExecWord")
public class ArgvExecWordEmitter extends AbstractExecWordEmitter {

	private StringTransform transform;

	public ArgvExecWordEmitter() {}

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
	protected void buildArgvImpl(Machine machine, Argv argv, String workdir, Map<String, String> environ,
			int flags, Consumer<String> sink) {
		for(String word : argv.asIterable())
			sink.accept(transform == null ? word : transform.transformString(word));
	}

}
