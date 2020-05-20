package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface NewTempFileWordEmitter {

	void buildArgv(Machine machine, Consumer<String> sink) throws PuppetException;

}
