package org.unclesniper.puppeteer;

public abstract class AbstractCopyInFileProvider extends AbstractTraceable implements CopyInFileProvider {

	public AbstractCopyInFileProvider() {}

	protected abstract void copyToImpl(CopySlave slave, MachineStep.MachineStepInfo info, String destination)
			throws PuppetException;

	@Override
	public void copyTo(CopySlave slave, MachineStep.MachineStepInfo info, String destination)
			throws PuppetException {
		try {
			copyToImpl(slave, info, destination);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
