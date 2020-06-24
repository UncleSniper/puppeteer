package org.unclesniper.puppeteer;

public abstract class AbstractCopyToStringSource extends AbstractTraceable implements CopyToStringSource {

	public AbstractCopyToStringSource() {}

	protected abstract void buildStringImpl(CopySlave.CopyToInfo info, StringBuilder sink) throws PuppetException;

	@Override
	public void buildString(CopySlave.CopyToInfo info, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
