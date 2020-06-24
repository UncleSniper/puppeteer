package org.unclesniper.puppeteer;

public abstract class AbstractDeleteFileStringSource extends AbstractTraceable implements DeleteFileStringSource {

	public AbstractDeleteFileStringSource() {}

	protected abstract void buildStringImpl(FileSlave.DeleteFileInfo info, StringBuilder sink)
			throws PuppetException;

	@Override
	public void buildString(FileSlave.DeleteFileInfo info, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
