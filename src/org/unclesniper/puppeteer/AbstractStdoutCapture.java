package org.unclesniper.puppeteer;

public abstract class AbstractStdoutCapture extends AbstractTraceable implements StdoutCapture {

	public AbstractStdoutCapture() {}

	protected abstract PuppetIORunnable captureStdoutImpl(MachineStep.MachineStepInfo info, ExecControl ctl)
			throws PuppetException;

	@Override
	public PuppetIORunnable captureStdout(MachineStep.MachineStepInfo info, ExecControl ctl) throws PuppetException {
		try {
			return captureStdoutImpl(info, ctl);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
