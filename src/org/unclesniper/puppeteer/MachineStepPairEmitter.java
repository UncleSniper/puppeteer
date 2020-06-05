package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;

public interface MachineStepPairEmitter extends Traceable {

	void buildMap(MachineStep.MachineStepInfo info, BiConsumer<String, String> sink) throws PuppetException;

}
