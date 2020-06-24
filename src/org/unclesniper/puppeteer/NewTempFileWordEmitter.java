package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface NewTempFileWordEmitter extends Traceable {

	void buildArgv(FileSlave.NewTempFileInfo info, Consumer<String> sink) throws PuppetException;

}
