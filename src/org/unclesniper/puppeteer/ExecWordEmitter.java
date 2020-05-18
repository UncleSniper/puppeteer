package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.function.Consumer;

public interface ExecWordEmitter {

	void buildArgv(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			Consumer<String> sink) throws PuppetException;

}
