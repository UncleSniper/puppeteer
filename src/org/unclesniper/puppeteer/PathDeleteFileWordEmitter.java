package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("pathDeleteFileWord")
public class PathDeleteFileWordEmitter extends AbstractDeleteFileWordEmitter {

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
	protected void buildArgvImpl(FileSlave.DeleteFileInfo info, Consumer<String> sink) {
		sink.accept(transform == null ? info.file : transform.transformString(info.file));
	}

}
