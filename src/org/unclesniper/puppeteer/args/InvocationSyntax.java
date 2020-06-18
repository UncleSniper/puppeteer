package org.unclesniper.puppeteer.args;

public class InvocationSyntax extends Syntax {

	private Syntax target;

	public InvocationSyntax() {}

	public InvocationSyntax(Syntax target) {
		this.target = target;
	}

	protected InvocationSyntax(InvocationSyntax invocation, int dummy) {
		super(invocation);
		target = invocation.target;
	}

	public Syntax getTarget() {
		return target;
	}

	public void setTarget(Syntax target) {
		this.target = target;
	}

	@Override
	protected void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException {
		info.addRoot(target, "target");
		computeFirstSetForChild(info, target, false, false);
	}

	@Override
	protected void computeFollowSetImpl(SetComputationInfo info) throws NullSyntaxException {
		info.addRoot(target, "target");
		computeFollowSetForChild(info, target, false);
	}

	@Override
	protected void computePathsImpl() {}

	@Override
	protected Syntax duplicate() {
		return new InvocationSyntax(this, 0);
	}

}
