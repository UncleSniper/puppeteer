package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractScopeWordEmitter extends AbstractTraceable implements ScopeWordEmitter {

	public AbstractScopeWordEmitter() {}

	protected abstract void buildArgvImpl(ScopeLevel scope, Consumer<String> sink) throws PuppetException;

	@Override
	public void buildArgv(ScopeLevel scope, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(scope, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
