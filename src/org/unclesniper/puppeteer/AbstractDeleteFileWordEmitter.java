package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractDeleteFileWordEmitter extends AbstractTraceable implements DeleteFileWordEmitter {

	public AbstractDeleteFileWordEmitter() {}

	protected abstract void buildArgvImpl(Machine machine, String file, Consumer<String> sink)
			throws PuppetException;

	@Override
	public void buildArgv(Machine machine, String file, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(machine, file, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
