package org.unclesniper.puppeteer;

public abstract class AbstractDeleteFileStringSource extends AbstractTraceable implements DeleteFileStringSource {

	public AbstractDeleteFileStringSource() {}

	protected abstract void buildStringImpl(Machine machine, String file, StringBuilder sink)
			throws PuppetException;

	@Override
	public void buildString(Machine machine, String file, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(machine, file, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
