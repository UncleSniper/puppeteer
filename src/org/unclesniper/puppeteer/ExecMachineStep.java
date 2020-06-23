package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.IOException;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class ExecMachineStep extends AbstractMachineStep {

	private final List<MachineStepWordEmitter> words = new LinkedList<MachineStepWordEmitter>();

	private final List<MachineStepStringSource> workDir = new LinkedList<MachineStepStringSource>();

	private final List<MachineStepPairEmitter> envPairs = new LinkedList<MachineStepPairEmitter>();

	private int flags;

	private boolean ignoreNonZeroStatus;

	private StdinProvider stdinProvider;

	private StdoutCapture stdoutCapture;

	private StderrCapture stderrCapture;

	private ExitStatusCapture exitStatusCapture;

	public ExecMachineStep() {}

	public void addWord(MachineStepWordEmitter word) {
		if(word != null)
			words.add(word);
	}

	public void addWord(String word) {
		if(word == null)
			return;
		StringMachineStepWordEmitter emitter = new StringMachineStepWordEmitter();
		emitter.addWord(word);
		words.add(emitter);
	}

	public void addWorkDir(MachineStepStringSource piece) {
		if(piece != null)
			workDir.add(piece);
	}

	public void addWorkDir(NetworkStepStringSource piece) {
		if(piece != null)
			workDir.add(new NetworkStepStringSourceMachineStepStringSource(piece));
	}

	public void addWorkDir(StepStringSource piece) {
		if(piece != null)
			workDir.add(new StepStringSourceMachineStepStringSource(piece));
	}

	public void addWorkDir(String piece) {
		if(piece != null)
			workDir.add(new StringMachineStepStringSource(piece));
	}

	public void addFlag(ExecFlag flag) {
		if(flag != null)
			flags |= flag.getFlag();
	}

	public boolean isIgnoreNonZeroStatus() {
		return ignoreNonZeroStatus;
	}

	public void setIgnoreNonZeroStatus(boolean ignoreNonZeroStatus) {
		this.ignoreNonZeroStatus = ignoreNonZeroStatus;
	}

	public StdinProvider getStdinProvider() {
		return stdinProvider;
	}

	public void setStdinProvider(StdinProvider stdinProvider) {
		this.stdinProvider = stdinProvider;
	}

	public StdoutCapture getStdoutCapture() {
		return stdoutCapture;
	}

	public void setStdoutCapture(StdoutCapture stdoutCapture) {
		this.stdoutCapture = stdoutCapture;
	}

	public StderrCapture getStderrCapture() {
		return stderrCapture;
	}

	public void setStderrCapture(StderrCapture stderrCapture) {
		this.stderrCapture = stderrCapture;
	}

	public ExitStatusCapture getExitStatusCapture() {
		return exitStatusCapture;
	}

	public void setExitStatusCapture(ExitStatusCapture exitStatusCapture) {
		this.exitStatusCapture = exitStatusCapture;
	}

	@Override
	protected void performImpl(MachineStepInfo info) throws PuppetException {
		List<String> argv = new LinkedList<String>();
		Consumer<String> argvSink = argv::add;
		for(MachineStepWordEmitter word : words)
			word.buildArgv(info, argvSink);
		if(argv.isEmpty())
			return;
		String wd = MachineStepStringSource.accumulate(workDir, info);
		if(wd.length() == 0)
			wd = null;
		Map<String, String> env = new HashMap<String, String>();
		BiConsumer<String, String> envSink = env::put;
		for(MachineStepPairEmitter emitter : envPairs)
			emitter.buildMap(info, envSink);
		if(env.isEmpty())
			env = null;
		Machine machine = info.getMachine();
		ExecControl ctl = machine.getExecSlave(true).execute(machine, argv, wd, env, flags);
		PuppetIORunnable waitTask = () -> {
			int status = ctl.wait(!ignoreNonZeroStatus);
			if(exitStatusCapture != null)
				exitStatusCapture.captureExitStatus(status);
		};
		try(PuppetIORunnable.TryJoin wait = PuppetIORunnable.tryJoin(waitTask)) {
			try(PuppetRunnable.TryJoin joinStdin = PuppetRunnable.tryJoin(stdinProvider == null
					? null : stdinProvider.provideStdin(info, ctl))) {
				try(PuppetRunnable.TryJoin joinStdout = PuppetRunnable.tryJoin(stdoutCapture == null
						? null : stdoutCapture.captureStdout(info, ctl))) {
					try(PuppetRunnable.TryJoin joinStderr = PuppetRunnable.tryJoin(stderrCapture == null
							? null : stderrCapture.captureStderr(info, ctl))) {}
				}
			}
		}
		catch(IOException ioe) {
			throw new FailedToExecuteIOPuppetException(argv, machine, ioe);
		}
	}

}
