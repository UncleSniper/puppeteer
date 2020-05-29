package org.unclesniper.puppeteer;

public abstract class AbstractFileSlave extends AbstractTraceable implements FileSlave {

	public AbstractFileSlave() {}

	protected abstract String newTempFileImpl(Machine machine) throws PuppetException;

	@Override
	public String newTempFile(Machine machine) throws PuppetException {
		try {
			return newTempFileImpl(machine);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract void deleteFileImpl(Machine machine, String file) throws PuppetException;

	@Override
	public void deleteFile(Machine machine, String file) throws PuppetException {
		try {
			deleteFileImpl(machine, file);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
