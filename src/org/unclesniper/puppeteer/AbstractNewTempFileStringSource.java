package org.unclesniper.puppeteer;

public abstract class AbstractNewTempFileStringSource extends AbstractTraceable implements NewTempFileStringSource {

	public AbstractNewTempFileStringSource() {}

	protected abstract void buildStringImpl(Machine machine, StringBuilder sink) throws PuppetException;

	@Override
	public void buildString(Machine machine, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(machine, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
