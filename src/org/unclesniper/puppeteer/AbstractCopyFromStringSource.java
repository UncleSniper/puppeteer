package org.unclesniper.puppeteer;

public abstract class AbstractCopyFromStringSource extends AbstractTraceable implements CopyFromStringSource {

	public AbstractCopyFromStringSource() {}

	protected abstract void buildStringImpl(Machine machine, String source, OutFile destination,
			StringBuilder sink) throws PuppetException;

	@Override
	public void buildString(Machine machine, String source, OutFile destination, StringBuilder sink)
			throws PuppetException {
		try {
			buildStringImpl(machine, source, destination, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
