package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface CopyToWordEmitter extends Traceable {

	void buildArgv(CopySlave.CopyToInfo info, Consumer<String> sink) throws PuppetException;

}
