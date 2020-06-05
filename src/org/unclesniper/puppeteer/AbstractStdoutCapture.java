package org.unclesniper.puppeteer;

public abstract class AbstractStdoutCapture extends AbstractTraceable implements StdoutCapture {

	public AbstractStdoutCapture() {}

	protected abstract PuppetRunnable captureStdoutImpl(MachineStep.MachineStepInfo info, ExecControl ctl)
			throws PuppetException;

	@Override
	public PuppetRunnable captureStdout(MachineStep.MachineStepInfo info, ExecControl ctl) throws PuppetException {
		try {
			return captureStdoutImpl(info, ctl);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
