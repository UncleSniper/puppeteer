package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractExecWordEmitter extends AbstractTraceable implements ExecWordEmitter {

	public AbstractExecWordEmitter() {}

	protected abstract void buildArgvImpl(Machine machine, Argv argv, String workdir, Map<String, String> environ,
			int flags, Consumer<String> sink) throws PuppetException;

	@Override
	public void buildArgv(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			Consumer<String> sink) throws PuppetException {
		try {
			buildArgvImpl(machine, argv, workdir, environ, flags, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
