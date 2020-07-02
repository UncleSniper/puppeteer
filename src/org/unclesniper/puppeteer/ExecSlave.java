package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.Collection;

public interface ExecSlave extends Traceable {

	public final class ExecInfo {

		public final MachineStep.MachineStepInfo stepInfo;

		public final Machine machine;

		public final Machine execHost;

		public final Argv argv;

		public final String workdir;

		public final Map<String, String> environ;

		public final int flags;

		public ExecInfo(MachineStep.MachineStepInfo stepInfo, Machine machine, Machine execHost, Argv argv,
				String workdir, Map<String, String> environ, int flags) {
			this.stepInfo = stepInfo;
			this.machine = machine;
			this.execHost = execHost;
			this.argv = argv;
			this.workdir = workdir;
			this.environ = environ;
			this.flags = flags;
		}

	}

	public static final int FL_STDIN_UTF8  = 001;
	public static final int FL_STDOUT_UTF8 = 002;
	public static final int FL_STDERR_UTF8 = 004;

	ExecControl execute(MachineStep.MachineStepInfo stepInfo, Machine machine, Collection<String> argv,
			String workdir, Map<String, String> environ, int flags) throws PuppetException;

	ExecControl execute(MachineStep.MachineStepInfo stepInfo, Machine machine, String[] argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException;

}
