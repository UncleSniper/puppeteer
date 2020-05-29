package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractNewTempFileWordEmitter extends AbstractTraceable implements NewTempFileWordEmitter {

	public AbstractNewTempFileWordEmitter() {}

	protected abstract void buildArgvImpl(Machine machine, Consumer<String> sink) throws PuppetException;

	@Override
	public void buildArgv(Machine machine, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(machine, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
