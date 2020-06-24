package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractNewTempFileWordEmitter extends AbstractTraceable implements NewTempFileWordEmitter {

	public AbstractNewTempFileWordEmitter() {}

	protected abstract void buildArgvImpl(FileSlave.NewTempFileInfo info, Consumer<String> sink)
			throws PuppetException;

	@Override
	public void buildArgv(FileSlave.NewTempFileInfo info, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
