package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.Collection;

public interface ExecSlave {

	public static final int FL_STDIN_UTF8  = 001;
	public static final int FL_STDOUT_UTF8 = 002;
	public static final int FL_STDERR_UTF8 = 004;

	ExecControl execute(Machine machine, Collection<String> argv, String workdir, Map<String, String> environ,
			int flags) throws PuppetException;

	ExecControl execute(Machine machine, String[] argv, String workdir, Map<String, String> environ, int flags)
			throws PuppetException;

}
