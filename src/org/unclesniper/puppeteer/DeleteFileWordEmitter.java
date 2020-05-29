package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface DeleteFileWordEmitter extends Traceable {

	void buildArgv(Machine machine, String file, Consumer<String> sink) throws PuppetException;

}
