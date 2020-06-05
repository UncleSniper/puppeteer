package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class StringMachineStepPairEmitter extends AbstractMachineStepPairEmitter {

	private final Map<String, String> pairs = new HashMap<String, String>();

	public StringMachineStepPairEmitter() {}

	public void putPair(String key, String value) {
		if(key != null && value != null)
			pairs.put(key, value);
	}

	@Override
	protected void buildMapImpl(MachineStep.MachineStepInfo info, BiConsumer<String, String> sink)
			throws PuppetException {
		for(Map.Entry<String, String> entry : pairs.entrySet())
			sink.accept(entry.getKey(), entry.getValue());
	}

}
