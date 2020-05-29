package org.unclesniper.puppeteer;

import java.util.Map;

public abstract class AbstractExecStringSource extends AbstractTraceable implements ExecStringSource {

	public AbstractExecStringSource() {}

	protected abstract void buildStringImpl(Machine machine, Argv argv, String workdir,
			Map<String, String> environ, int flags, StringBuilder sink) throws PuppetException;

	@Override
	public void buildString(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(machine, argv, workdir, environ, flags, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
