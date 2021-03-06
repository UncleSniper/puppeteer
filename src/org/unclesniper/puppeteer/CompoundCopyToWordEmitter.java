package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("compoundCopyToWord")
public class CompoundCopyToWordEmitter extends AbstractCopyToWordEmitter {

	private final List<CopyToStringSource> pieces = new LinkedList<CopyToStringSource>();

	private StringTransform transform;

	public CompoundCopyToWordEmitter() {}

	public void addPiece(CopyToStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(String piece) {
		if(piece == null)
			return;
		StringCopyToStringSource string = new StringCopyToStringSource(piece);
		string.ingestObjectDefinitionLocation(this);
		pieces.add(string);
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
	protected void buildArgvImpl(CopySlave.CopyToInfo info, Consumer<String> sink)
			throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(CopyToStringSource piece : pieces)
			piece.buildString(info, builder);
		String word = builder.toString();
		sink.accept(transform == null ? word : transform.transformString(word));
	}

}
