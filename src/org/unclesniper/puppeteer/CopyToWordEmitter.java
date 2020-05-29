package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface CopyToWordEmitter extends Traceable {

	void buildArgv(Machine machine, InFile source, String destination, Consumer<String> sink)
			throws PuppetException;

}
