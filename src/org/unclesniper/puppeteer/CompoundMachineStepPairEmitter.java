package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("compoundMachineStepPair")
public class CompoundMachineStepPairEmitter extends AbstractMachineStepPairEmitter {

	private final List<MachineStepStringSource> keyPieces = new LinkedList<MachineStepStringSource>();

	private final List<MachineStepStringSource> valuePieces = new LinkedList<MachineStepStringSource>();

	private StringTransform keyTransform;

	private StringTransform valueTransform;

	public CompoundMachineStepPairEmitter() {}

	public void addKeyPiece(MachineStepStringSource piece) {
		if(piece != null)
			keyPieces.add(piece);
	}

	public void addKeyPiece(NetworkStepStringSource piece) {
		if(piece != null)
			keyPieces.add(new NetworkStepStringSourceMachineStepStringSource(piece));
	}

	public void addKeyPiece(StepStringSource piece) {
		if(piece != null)
			keyPieces.add(new StepStringSourceMachineStepStringSource(piece));
	}

	public void addKeyPiece(String piece) {
		if(piece != null)
			keyPieces.add(new StringMachineStepStringSource(piece));
	}

	public void addValuePiece(MachineStepStringSource piece) {
		if(piece != null)
			valuePieces.add(piece);
	}

	public void addValuePiece(NetworkStepStringSource piece) {
		if(piece != null)
			valuePieces.add(new NetworkStepStringSourceMachineStepStringSource(piece));
	}

	public void addValuePiece(StepStringSource piece) {
		if(piece != null)
			valuePieces.add(new StepStringSourceMachineStepStringSource(piece));
	}

	public void addValuePiece(String piece) {
		if(piece != null)
			valuePieces.add(new StringMachineStepStringSource(piece));
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
	protected void buildMapImpl(MachineStep.MachineStepInfo info, BiConsumer<String, String> sink)
			throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(MachineStepStringSource piece : keyPieces)
			piece.buildString(info, builder);
		String key = builder.toString();
		builder.setLength(0);
		for(MachineStepStringSource piece : valuePieces)
			piece.buildString(info, builder);
		String value = builder.toString();
		if(keyTransform != null)
			key = keyTransform.transformString(key);
		if(valueTransform != null)
			value = valueTransform.transformString(value);
		sink.accept(key, value);
	}

}
