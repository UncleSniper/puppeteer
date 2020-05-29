package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.Collection;

public abstract class AbstractExecSlave extends AbstractTraceable implements ExecSlave {

	public AbstractExecSlave() {}

	protected abstract ExecControl executeImpl(Machine machine, Collection<String> argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException;

	@Override
	public ExecControl execute(Machine machine, Collection<String> argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException {
		try {
			return executeImpl(machine, argv, workdir, environ, flags);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract ExecControl executeImpl(Machine machine, String[] argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException;

	@Override
	public ExecControl execute(Machine machine, String[] argv, String workdir, Map<String, String> environ,
			int flags) throws PuppetException {
		try {
			return executeImpl(machine, argv, workdir, environ, flags);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
