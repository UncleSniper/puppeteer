package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface DeleteFileWordEmitter extends Traceable {

	void buildArgv(FileSlave.DeleteFileInfo info, Consumer<String> sink) throws PuppetException;

}
