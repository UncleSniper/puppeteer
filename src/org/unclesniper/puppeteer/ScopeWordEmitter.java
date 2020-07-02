package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public interface ScopeWordEmitter extends Traceable {

	void buildArgv(ScopeLevel scope, Consumer<String> sink) throws PuppetException;

}
