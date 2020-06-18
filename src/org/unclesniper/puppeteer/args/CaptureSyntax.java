package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.Argument;

public class CaptureSyntax extends Syntax {

	private Argument argument;

	public CaptureSyntax() {}

	public CaptureSyntax(Argument argument) {
		this.argument = argument;
	}

	protected CaptureSyntax(CaptureSyntax capture) {
		super(capture);
		argument = capture.argument;
	}

	public Argument getArgument() {
		return argument;
	}

	public void setArgument(Argument argument) {
		this.argument = argument;
	}

	@Override
	protected void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException {
		if(firstSet.getAny() == null) {
			firstSet.setAny(this);
			info.modified = true;
		}
	}

	@Override
	protected void computeFollowSetImpl(SetComputationInfo info) {}

	@Override
	protected void computePathsImpl() {}

	@Override
	protected Syntax duplicate() {
		return new CaptureSyntax(this);
	}

}
