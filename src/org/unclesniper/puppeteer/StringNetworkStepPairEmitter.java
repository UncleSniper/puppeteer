package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.HashMap;
import java.util.function.BiConsumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkStepPair")
public class StringNetworkStepPairEmitter extends AbstractNetworkStepPairEmitter {

	private final Map<String, String> pairs = new HashMap<String, String>();

	public StringNetworkStepPairEmitter() {}

	public void putPair(String key, String value) {
		if(key != null && value != null)
			pairs.put(key, value);
	}

	@Override
	protected void buildMapImpl(NetworkStep.NetworkStepInfo info, BiConsumer<String, String> sink) {
		for(Map.Entry<String, String> entry : pairs.entrySet())
			sink.accept(entry.getKey(), entry.getValue());
	}

}
