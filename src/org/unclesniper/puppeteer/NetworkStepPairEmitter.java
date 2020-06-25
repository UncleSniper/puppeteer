package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;

public interface NetworkStepPairEmitter extends Traceable {

	void buildMap(NetworkStep.NetworkStepInfo info, BiConsumer<String, String> sink) throws PuppetException;

}
