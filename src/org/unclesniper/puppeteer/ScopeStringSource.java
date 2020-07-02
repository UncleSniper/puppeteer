package org.unclesniper.puppeteer;

public interface ScopeStringSource extends Traceable {

	void buildString(ScopeLevel scope, StringBuilder sink) throws PuppetException;

	public static String accumulate(Iterable<ScopeStringSource> pieces, ScopeLevel scope) throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(ScopeStringSource piece : pieces)
			piece.buildString(scope, builder);
		return builder.toString();
	}

}
