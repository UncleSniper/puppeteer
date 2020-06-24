package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("compoundNetworkStepWord")
public class CompoundNetworkStepWordEmitter extends AbstractNetworkStepWordEmitter {

	private final List<NetworkStepStringSource> pieces = new LinkedList<NetworkStepStringSource>();

	private StringTransform transform;

	public CompoundNetworkStepWordEmitter() {}

	public void addPiece(NetworkStepStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(StepStringSource piece) {
		if(piece != null)
			pieces.add(new StepStringSourceNetworkStepStringSource(piece));
	}

	public void addPiece(String piece) {
		if(piece != null)
			pieces.add(new StringNetworkStepStringSource(piece));
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
	protected void buildArgvImpl(NetworkStep.NetworkStepInfo info, Consumer<String> sink) throws PuppetException {
		StringBuilder builder = new StringBuilder();

		for(NetworkStepStringSource piece : pieces)
			piece.buildString(info, builder);
		String word = builder.toString();
		sink.accept(transform == null ? word : transform.transformString(word));
	}

}
