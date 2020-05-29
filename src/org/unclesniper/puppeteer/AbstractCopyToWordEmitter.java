package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractCopyToWordEmitter extends AbstractTraceable implements CopyToWordEmitter {

	public AbstractCopyToWordEmitter() {}

	protected abstract void buildArgvImpl(Machine machine, InFile source, String destination, Consumer<String> sink)
			throws PuppetException;

	@Override
	public void buildArgv(Machine machine, InFile source, String destination, Consumer<String> sink)
			throws PuppetException {
		try {
			buildArgvImpl(machine, source, destination, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
