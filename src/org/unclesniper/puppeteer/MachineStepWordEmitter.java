package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface MachineStepWordEmitter extends Traceable {

	void buildArgv(MachineStep.MachineStepInfo info, Consumer<String> sink) throws PuppetException;

}
