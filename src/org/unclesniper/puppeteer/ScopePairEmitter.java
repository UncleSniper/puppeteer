package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;

public interface ScopePairEmitter extends Traceable {

	void buildMap(ScopeLevel scope, BiConsumer<String, String> sink) throws PuppetException;

}
