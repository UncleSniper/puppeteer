package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("compoundStepWord")
public class CompoundStepWordEmitter extends AbstractStepWordEmitter {

	private final List<StepStringSource> pieces = new LinkedList<StepStringSource>();

	private StringTransform transform;

	public CompoundStepWordEmitter() {}

	public void addPiece(StepStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(String piece) {
		if(piece == null)
			return;
		StringStepStringSource string = new StringStepStringSource(piece);
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
	protected void buildArgvImpl(Step.StepInfo info, Consumer<String> sink) throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(StepStringSource piece : pieces)
			piece.buildString(info, builder);
		String word = builder.toString();
		sink.accept(transform == null ? word : transform.transformString(word));
	}

}
