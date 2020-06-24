package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractDeleteFileWordEmitter extends AbstractTraceable implements DeleteFileWordEmitter {

	public AbstractDeleteFileWordEmitter() {}

	protected abstract void buildArgvImpl(FileSlave.DeleteFileInfo info, Consumer<String> sink)
			throws PuppetException;

	@Override
	public void buildArgv(FileSlave.DeleteFileInfo info, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
