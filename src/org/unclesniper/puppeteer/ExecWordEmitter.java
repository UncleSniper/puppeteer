package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.function.Consumer;

public interface ExecWordEmitter extends Traceable {

	void buildArgv(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			Consumer<String> sink) throws PuppetException;

}
