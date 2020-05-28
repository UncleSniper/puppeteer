package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface NetworkStepWordEmitter {

	void buildArgv(NetworkStep.NetworkStepInfo info, Consumer<String> sink) throws PuppetException;

}
