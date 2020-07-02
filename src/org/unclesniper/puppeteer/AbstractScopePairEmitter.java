package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;

public abstract class AbstractScopePairEmitter extends AbstractTraceable implements ScopePairEmitter {

	public AbstractScopePairEmitter() {}

	protected abstract void buildMapImpl(ScopeLevel scope, BiConsumer<String, String> sink)
			throws PuppetException;

	@Override
	public void buildMap(ScopeLevel scope, BiConsumer<String, String> sink) throws PuppetException {
		try {
			buildMapImpl(scope, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
