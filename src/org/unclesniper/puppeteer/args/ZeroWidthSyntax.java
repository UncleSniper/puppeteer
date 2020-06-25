package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.PuppetException;

public abstract class ZeroWidthSyntax extends Syntax {

	public ZeroWidthSyntax() {}

	protected ZeroWidthSyntax(ZeroWidthSyntax zeroWidth) {
		super(zeroWidth);
	}

	protected abstract void seen(ScopeLevel scope) throws PuppetException;

	@Override
	protected void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException {
		if(firstSet.getEnd() == null) {
			firstSet.setEnd(this);
			info.modified = true;
		}
	}

	@Override
	protected void computeFollowSetImpl(SetComputationInfo info) {}

	@Override
	protected void computePathsImpl() {}

	@Override
	protected void parseImpl(ScopeLevel scope, ArgumentSource source) throws PuppetException {
		seen(scope);
	}

	@Override
	protected void initializeParseImpl(ScopeLevel scope, SetComputationInfo info) {}

}
