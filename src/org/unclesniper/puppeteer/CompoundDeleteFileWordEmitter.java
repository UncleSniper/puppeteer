package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class CompoundDeleteFileWordEmitter extends AbstractDeleteFileWordEmitter {

	private final List<DeleteFileStringSource> pieces = new LinkedList<DeleteFileStringSource>();

	private StringTransform transform;

	public CompoundDeleteFileWordEmitter() {}

	public void addPiece(DeleteFileStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(String piece) {
		if(piece != null)
			pieces.add(new StringDeleteFileStringSource(piece));
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
	protected void buildArgvImpl(Machine machine, String file, Consumer<String> sink) throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(DeleteFileStringSource piece : pieces)
			piece.buildString(machine, file, builder);
		String word = builder.toString();
		sink.accept(transform == null ? word : transform.transformString(word));
	}

}
