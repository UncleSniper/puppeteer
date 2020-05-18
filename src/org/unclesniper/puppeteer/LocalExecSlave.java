package org.unclesniper.puppeteer;

import java.io.File;
import java.util.Map;
import java.io.IOException;
import java.util.Collection;

public class LocalExecSlave implements ExecSlave {

	public static final LocalExecSlave instance = new LocalExecSlave();

	public LocalExecSlave() {}

	public ExecControl execute(Machine machine, Collection<String> argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException {
		return ExecUtils.useArray(this, machine, argv, workdir, environ, flags);
	}

	public ExecControl execute(Machine machine, String[] argv, String workdir, Map<String, String> environ,
			int flags) throws PuppetException {
		ProcessBuilder builder = new ProcessBuilder(argv);
		if(workdir != null)
			builder.directory(new File(workdir));
		if(environ != null)
			builder.environment().putAll(environ);
		try {
			return new ProcessExecControl(builder.start(), flags);
		}
		catch(IOException ioe) {
			throw new LocalExecFailedException(argv, ioe);
		}
	}

}
