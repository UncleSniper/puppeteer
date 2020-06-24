package org.unclesniper.puppeteer.args;

import java.util.Map;
import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("terminalSyntax")
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

	@Override
	protected void parseImpl(ScopeLevel scope, ArgumentSource source) throws PuppetException {
		String token = source.current();
		if(!literal.equals(token))
			throw new ArgumentSyntaxException('\'' + literal + '\'', token, source.location());
		source.next();
	}

	@Override
	protected void initializeParseImpl(ScopeLevel scope, SetComputationInfo info) {}

}
