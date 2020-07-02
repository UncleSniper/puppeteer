package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("compoundMachineStepWord")
public class CompoundMachineStepWordEmitter extends AbstractMachineStepWordEmitter {

	private final List<MachineStepStringSource> pieces = new LinkedList<MachineStepStringSource>();

	private StringTransform transform;

	public CompoundMachineStepWordEmitter() {}

	public void addPiece(MachineStepStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(NetworkStepStringSource piece) {
		if(piece != null)
			pieces.add(new NetworkStepStringSourceMachineStepStringSource(piece));
	}

	public void addPiece(StepStringSource piece) {
		if(piece != null)
			pieces.add(new StepStringSourceMachineStepStringSource(piece));
	}

	public void addPiece(String piece) {
		if(piece == null)
			return;
		StringMachineStepStringSource string = new StringMachineStepStringSource(piece);
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
	protected void buildArgvImpl(MachineStep.MachineStepInfo info, Consumer<String> sink) throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(MachineStepStringSource piece : pieces)
			piece.buildString(info, builder);
		String word = builder.toString();
		sink.accept(transform == null ? word : transform.transformString(word));
	}

}
