package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class CompoundExecWordEmitter extends AbstractExecWordEmitter {

	private final List<ExecStringSource> pieces = new LinkedList<ExecStringSource>();

	private StringTransform transform;

	public CompoundExecWordEmitter() {}

	public void addPiece(ExecStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(String piece) {
		if(piece != null)
			pieces.add(new StringExecStringSource(piece));
	}

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
			int flags, Consumer<String> sink) throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(ExecStringSource piece : pieces)
			piece.buildString(machine, argv, workdir, environ, flags, builder);
		String word = builder.toString();
		sink.accept(transform == null ? word : transform.transformString(word));
	}

}
