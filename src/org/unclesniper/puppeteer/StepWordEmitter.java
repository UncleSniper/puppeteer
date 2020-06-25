package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface StepWordEmitter extends Traceable {

	void buildArgv(Step.StepInfo info, Consumer<String> sink) throws PuppetException;

}
