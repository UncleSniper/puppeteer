package org.unclesniper.puppeteer;

public abstract class AbstractCopyFromStringSource extends AbstractTraceable implements CopyFromStringSource {

	public AbstractCopyFromStringSource() {}

	protected abstract void buildStringImpl(CopySlave.CopyFromInfo info, StringBuilder sink) throws PuppetException;

	@Override
	public void buildString(CopySlave.CopyFromInfo info, StringBuilder sink)
			throws PuppetException {
		try {
			buildStringImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
