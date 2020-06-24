package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractCopyToWordEmitter extends AbstractTraceable implements CopyToWordEmitter {

	public AbstractCopyToWordEmitter() {}

	protected abstract void buildArgvImpl(CopySlave.CopyToInfo info, Consumer<String> sink)
			throws PuppetException;

	@Override
	public void buildArgv(CopySlave.CopyToInfo info, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
