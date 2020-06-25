package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractCopyFromWordEmitter extends AbstractTraceable implements CopyFromWordEmitter {

	public AbstractCopyFromWordEmitter() {}

	protected abstract void buildArgvImpl(CopySlave.CopyFromInfo info, Consumer<String> sink) throws PuppetException;

	@Override
	public void buildArgv(CopySlave.CopyFromInfo info, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
