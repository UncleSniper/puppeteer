package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface ExecWordEmitter extends Traceable {

	void buildArgv(ExecSlave.ExecInfo info, Consumer<String> sink) throws PuppetException;

}
