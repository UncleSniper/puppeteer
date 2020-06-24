package org.unclesniper.puppeteer;

public abstract class AbstractExecStringSource extends AbstractTraceable implements ExecStringSource {

	public AbstractExecStringSource() {}

	protected abstract void buildStringImpl(ExecSlave.ExecInfo info, StringBuilder sink) throws PuppetException;

	@Override
	public void buildString(ExecSlave.ExecInfo info, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
