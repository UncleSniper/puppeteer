package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class CompoundCopyFromWordEmitter implements CopyFromWordEmitter {

	private final List<CopyFromStringSource> pieces = new LinkedList<CopyFromStringSource>();

	private StringTransform transform;

	public CompoundCopyFromWordEmitter() {}

	public void addPiece(CopyFromStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(String piece) {
		if(piece != null)
			pieces.add(new StringCopyFromStringSource(piece));
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
	public void buildArgv(Machine machine, String source, OutFile destination, Consumer<String> sink)
			throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(CopyFromStringSource piece : pieces)
			piece.buildString(machine, source, destination, builder);
		String word = builder.toString();
		sink.accept(transform == null ? word : transform.transformString(word));
	}

}
