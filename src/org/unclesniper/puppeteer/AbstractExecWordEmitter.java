package org.unclesniper.puppeteer;

import java.util.function.Consumer;

public abstract class AbstractExecWordEmitter extends AbstractTraceable implements ExecWordEmitter {

	public AbstractExecWordEmitter() {}

	protected abstract void buildArgvImpl(ExecSlave.ExecInfo info, Consumer<String> sink) throws PuppetException;

	@Override
	public void buildArgv(ExecSlave.ExecInfo info, Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
