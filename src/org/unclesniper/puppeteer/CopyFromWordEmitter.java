package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface CopyFromWordEmitter extends Traceable {

	void buildArgv(Machine machine, String source, OutFile destination, Consumer<String> sink)
			throws PuppetException;

}
