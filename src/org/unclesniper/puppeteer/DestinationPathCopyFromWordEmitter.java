package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("destinationPathCopyFromWord")
public class DestinationPathCopyFromWordEmitter extends AbstractCopyFromWordEmitter {

	private StringTransform transform;

	public DestinationPathCopyFromWordEmitter() {}

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
	protected void buildArgvImpl(Machine machine, String source, OutFile destination, Consumer<String> sink)
			throws PuppetException {
		String path = destination.asFile();
		sink.accept(transform == null ? path : transform.transformString(path));
	}

}
