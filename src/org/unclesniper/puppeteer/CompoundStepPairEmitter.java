package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("compoundStepPair")
public class CompoundStepPairEmitter extends AbstractStepPairEmitter {

	private final List<StepStringSource> keyPieces = new LinkedList<StepStringSource>();

	private final List<StepStringSource> valuePieces = new LinkedList<StepStringSource>();

	private StringTransform keyTransform;

	private StringTransform valueTransform;

	public CompoundStepPairEmitter() {}

	public void addKeyPiece(StepStringSource piece) {
		if(piece != null)
			keyPieces.add(piece);
	}

	public void addKeyPiece(String piece) {
		if(piece != null)
			keyPieces.add(new StringStepStringSource(piece));
	}

	public void addValuePiece(StepStringSource piece) {
		if(piece != null)
			valuePieces.add(piece);
	}

	public void addValuePiece(String piece) {
		if(piece != null)
			valuePieces.add(new StringStepStringSource(piece));
	}

	public StringTransform getKeyTransform() {
		return keyTransform;
	}

	public void setKeyTransform(StringTransform keyTransform) {
		this.keyTransform = keyTransform;
	}

	public void setKeyTransform(WordQuoter keyTransform) {
		this.keyTransform = keyTransform == null ? null : new QuotingStringTransform(keyTransform);
	}

	public StringTransform getValueTransform() {
		return valueTransform;
	}

	public void setValueTransform(StringTransform valueTransform) {
		this.valueTransform = valueTransform;
	}

	public void setValueTransform(WordQuoter valueTransform) {
		this.valueTransform = valueTransform == null ? null : new QuotingStringTransform(valueTransform);
	}

	@Override
	protected void buildMapImpl(Step.StepInfo info, BiConsumer<String, String> sink) throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(StepStringSource piece : keyPieces)
			piece.buildString(info, builder);
		String key = builder.toString();
		builder.setLength(0);
		for(StepStringSource piece : valuePieces)
			piece.buildString(info, builder);
		String value = builder.toString();
		if(keyTransform != null)
			key = keyTransform.transformString(key);
		if(valueTransform != null)
			value = valueTransform.transformString(value);
		sink.accept(key, value);
	}

}
