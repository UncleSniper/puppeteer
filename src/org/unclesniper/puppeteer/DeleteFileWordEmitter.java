package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface DeleteFileWordEmitter {

	void buildArgv(Machine machine, String file, Consumer<String> sink) throws PuppetException;

}
