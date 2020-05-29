package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractCopyFromWordEmitter extends AbstractTraceable implements CopyFromWordEmitter {

	public AbstractCopyFromWordEmitter() {}

	protected abstract void buildArgvImpl(Machine machine, String source, OutFile destination,
			Consumer<String> sink) throws PuppetException;

	@Override
	public void buildArgv(Machine machine, String source, OutFile destination, Consumer<String> sink)
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
