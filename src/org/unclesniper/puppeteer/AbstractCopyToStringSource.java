package org.unclesniper.puppeteer;

public abstract class AbstractCopyToStringSource extends AbstractTraceable implements CopyToStringSource {

	public AbstractCopyToStringSource() {}

	protected abstract void buildStringImpl(Machine machine, InFile source, String destination, StringBuilder sink)
			throws PuppetException;

	@Override
	public void buildString(Machine machine, InFile source, String destination, StringBuilder sink)
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
