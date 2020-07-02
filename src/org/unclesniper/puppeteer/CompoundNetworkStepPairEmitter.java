package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("compoundNetworkStepPair")
public class CompoundNetworkStepPairEmitter extends AbstractNetworkStepPairEmitter {

	private final List<NetworkStepStringSource> keyPieces = new LinkedList<NetworkStepStringSource>();

	private final List<NetworkStepStringSource> valuePieces = new LinkedList<NetworkStepStringSource>();

	private StringTransform keyTransform;

	private StringTransform valueTransform;

	public CompoundNetworkStepPairEmitter() {}

	public void addKeyPiece(NetworkStepStringSource piece) {
		if(piece != null)
			keyPieces.add(piece);
	}

	public void addKeyPiece(StepStringSource piece) {
		if(piece != null)
			keyPieces.add(new StepStringSourceNetworkStepStringSource(piece));
	}

	public void addKeyPiece(String piece) {
		if(piece == null)
			return;
		StringNetworkStepStringSource string = new StringNetworkStepStringSource(piece);
		string.ingestObjectDefinitionLocation(this);
		keyPieces.add(string);
	}

	public void addValuePiece(NetworkStepStringSource piece) {
		if(piece != null)
			valuePieces.add(piece);
	}

	public void addValuePiece(StepStringSource piece) {
		if(piece != null)
			valuePieces.add(new StepStringSourceNetworkStepStringSource(piece));
	}

	public void addValuePiece(String piece) {
		if(piece == null)
			return;
		StringNetworkStepStringSource string = new StringNetworkStepStringSource(piece);
		string.ingestObjectDefinitionLocation(this);
		valuePieces.add(string);
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
	protected void buildMapImpl(NetworkStep.NetworkStepInfo info, BiConsumer<String, String> sink)
			throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(NetworkStepStringSource piece : keyPieces)
			piece.buildString(info, builder);
		String key = builder.toString();
		builder.setLength(0);
		for(NetworkStepStringSource piece : valuePieces)
			piece.buildString(info, builder);
		String value = builder.toString();
		if(keyTransform != null)
			key = keyTransform.transformString(key);
		if(valueTransform != null)
			value = valueTransform.transformString(value);
		sink.accept(key, value);
	}

}
