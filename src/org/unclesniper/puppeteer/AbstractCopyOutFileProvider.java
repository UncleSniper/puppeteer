package org.unclesniper.puppeteer;

public abstract class AbstractCopyOutFileProvider extends AbstractTraceable implements CopyOutFileProvider {

	public AbstractCopyOutFileProvider() {}

	protected abstract void copyFromImpl(CopySlave slave, MachineStep.MachineStepInfo info, String source)
			throws PuppetException;

	@Override
	public void copyFrom(CopySlave slave, MachineStep.MachineStepInfo info, String source)
			throws PuppetException {
		try {
			copyFromImpl(slave, info, source);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
