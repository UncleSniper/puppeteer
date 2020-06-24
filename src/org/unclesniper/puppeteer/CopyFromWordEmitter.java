package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface CopyFromWordEmitter extends Traceable {

	void buildArgv(CopySlave.CopyFromInfo info, Consumer<String> sink)
			throws PuppetException;

}
