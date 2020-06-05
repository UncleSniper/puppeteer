package org.unclesniper.puppeteer;

public abstract class AbstractStderrCapture extends AbstractTraceable implements StderrCapture {

	public AbstractStderrCapture() {}

	protected abstract PuppetRunnable captureStderrImpl(MachineStep.MachineStepInfo info, ExecControl ctl)
			throws PuppetException;

	@Override
	public PuppetRunnable captureStderr(MachineStep.MachineStepInfo info, ExecControl ctl) throws PuppetException {
		try {
			return captureStderrImpl(info, ctl);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
