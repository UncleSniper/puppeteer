package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface CopyFromWordEmitter {

	void buildArgv(Machine machine, String source, OutFile destination, Consumer<String> sink)
			throws PuppetException;

}
