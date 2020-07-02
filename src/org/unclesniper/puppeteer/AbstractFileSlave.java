package org.unclesniper.puppeteer;

public abstract class AbstractFileSlave extends AbstractTraceable implements FileSlave {

	public AbstractFileSlave() {}

	protected abstract String newTempFileImpl(MachineStep.MachineStepInfo stepInfo, Machine machine)
			throws PuppetException;

	@Override
	public String newTempFile(MachineStep.MachineStepInfo stepInfo, Machine machine) throws PuppetException {
		try {
			return newTempFileImpl(stepInfo, machine);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract void deleteFileImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, String file)
			throws PuppetException;

	@Override
	public void deleteFile(MachineStep.MachineStepInfo stepInfo, Machine machine, String file)
			throws PuppetException {
		try {
			deleteFileImpl(stepInfo, machine, file);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
