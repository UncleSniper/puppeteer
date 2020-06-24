package org.unclesniper.puppeteer;

public abstract class AbstractNewTempFileStringSource extends AbstractTraceable implements NewTempFileStringSource {

	public AbstractNewTempFileStringSource() {}

	protected abstract void buildStringImpl(FileSlave.NewTempFileInfo info, StringBuilder sink)
			throws PuppetException;

	@Override
	public void buildString(FileSlave.NewTempFileInfo info, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
