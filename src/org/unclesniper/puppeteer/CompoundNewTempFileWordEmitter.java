package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class CompoundNewTempFileWordEmitter implements NewTempFileWordEmitter {

	private final List<NewTempFileStringSource> pieces = new LinkedList<NewTempFileStringSource>();

	private StringTransform transform;

	public CompoundNewTempFileWordEmitter() {}

	public void addPiece(NewTempFileStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(String piece) {
		if(piece != null)
			pieces.add(new StringNewTempFileStringSource(piece));
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
	public void buildArgv(Machine machine, Consumer<String> sink) throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(NewTempFileStringSource piece : pieces)
			piece.buildString(machine, builder);
		String word = builder.toString();
		sink.accept(transform == null ? word : transform.transformString(word));
	}

}
