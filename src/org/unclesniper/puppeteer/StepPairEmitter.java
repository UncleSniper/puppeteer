package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;

public interface StepPairEmitter extends Traceable {

	void buildMap(Step.StepInfo info, BiConsumer<String, String> sink) throws PuppetException;

}
