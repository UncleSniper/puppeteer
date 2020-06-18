package org.unclesniper.puppeteer.args;

import java.util.Map;

public class TerminalSyntax extends Syntax {

	private String literal;

	public TerminalSyntax() {}

	public TerminalSyntax(String literal) {
		this.literal = literal;
	}

	protected TerminalSyntax(TerminalSyntax terminal) {
		super(terminal);
		literal = terminal.literal;
	}

	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	@Override
	protected void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException {
		if(literal == null)
			throw new NullSyntaxException("literal");
		Map<String, Syntax> literals = firstSet.getLiterals();
		if(!literals.containsKey(literal)) {
			literals.put(literal, this);
			info.modified = true;
		}
	}

	@Override
	protected void computeFollowSetImpl(SetComputationInfo info) {}

	@Override
	protected void computePathsImpl() {}

	@Override
	protected Syntax duplicate() {
		return new TerminalSyntax(this);
	}

}
