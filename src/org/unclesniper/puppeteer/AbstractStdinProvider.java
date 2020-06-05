package org.unclesniper.puppeteer;

public abstract class AbstractStdinProvider extends AbstractTraceable implements StdinProvider {

	public AbstractStdinProvider() {}

	protected abstract PuppetRunnable provideStdinImpl(MachineStep.MachineStepInfo info, ExecControl ctl)
			throws PuppetException;

	@Override
	public PuppetRunnable provideStdin(MachineStep.MachineStepInfo info, ExecControl ctl) throws PuppetException {
		try {
			return provideStdinImpl(info, ctl);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
