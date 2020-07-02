package org.unclesniper.puppeteer;

public abstract class AbstractScopeStringSource extends AbstractTraceable implements ScopeStringSource {

	public AbstractScopeStringSource() {}

	protected abstract void buildStringImpl(ScopeLevel scope, StringBuilder sink) throws PuppetException;

	@Override
	public void buildString(ScopeLevel scope, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(scope, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
