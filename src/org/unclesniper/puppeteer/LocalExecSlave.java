package org.unclesniper.puppeteer;

import java.io.File;
import java.util.Map;
import java.io.IOException;
import java.util.Collection;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("localExec")
public class LocalExecSlave extends AbstractExecSlave {

	public static final LocalExecSlave instance = new LocalExecSlave();

	public LocalExecSlave() {}

	@Override
	protected ExecControl executeImpl(Machine machine, Collection<String> argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException {
		return ExecUtils.useArray(this, machine, argv, workdir, environ, flags);
	}

	@Override
	protected ExecControl executeImpl(Machine machine, String[] argv, String workdir, Map<String, String> environ,
			int flags) throws PuppetException {
		if(LocalUtils.DEBUG_LOCAL) {
			System.err.println("*** DEBUG: execute:");
			System.err.println("***            argv:");
			for(String word : argv)
				System.err.println("***                '" + word + '\'');
			System.err.println("***            workdir: " + (workdir == null ? "<null>" : '\'' + workdir + '\''));
			if(environ != null && !environ.isEmpty()) {
				System.err.println("***            environ:");
				for(Map.Entry<String, String> entry : environ.entrySet())
					System.err.println("***                '" + entry.getKey() + "'='" + entry.getValue() + '\'');
			}
		}
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
