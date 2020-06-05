package org.unclesniper.puppeteer;

public abstract class AbstractMachineStringCapture extends AbstractTraceable implements MachineStringCapture {

	public AbstractMachineStringCapture() {}

	protected abstract void captureStringImpl(MachineStep.MachineStepInfo info, String value)
			throws PuppetException;

	@Override
	public void captureString(MachineStep.MachineStepInfo info, String value) throws PuppetException {
		try {
			captureStringImpl(info, value);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
