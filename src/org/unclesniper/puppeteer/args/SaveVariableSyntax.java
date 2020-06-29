package org.unclesniper.puppeteer.args;

import java.util.Map;
import java.util.IdentityHashMap;
import org.unclesniper.puppeteer.Variable;
import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("saveVarSyntax")
public class SaveVariableSyntax extends Syntax {

	private Syntax child;

	private final Map<Variable, Variable> variables = new IdentityHashMap<Variable, Variable>();

	public SaveVariableSyntax() {}

	public SaveVariableSyntax(Syntax child) {
		this.child = child;
	}

	protected SaveVariableSyntax(SaveVariableSyntax saveVar, int dummy) {
		super(saveVar);
		child = saveVar.child == null ? null : saveVar.child.duplicate();
		variables.putAll(saveVar.variables);
	}

	public Syntax getChild() {
		return child;
	}

	public void setChild(Syntax child) {
		this.child = child;
	}

	public void addVariable(Variable variable) {
		if(variable != null)
			variables.put(variable, variable);
	}

	@Override
	protected void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException {
		if(child == null)
			throw new NullSyntaxException("child");
		computeFirstSetForChild(info, child, false, true);
	}

	@Override
	protected void computeFollowSetImpl(SetComputationInfo info) throws NullSyntaxException {
		computeFollowSetForChild(info, child, true);
	}

	@Override
	protected void computePathsImpl() throws LL1ityException {
		child.computePaths();
	}

	@Override
	protected Syntax duplicate() {
		return new SaveVariableSyntax(this, 0);
	}

	@Override
	protected void parseImpl(ScopeLevel scope, ArgumentSource source) throws PuppetException {
		try(Variable.ValueRestorer restorer = Variable.saveAllValues(variables.keySet(), scope)) {
			child.parse(scope, source);
		}
	}

	@Override
	protected void initializeParseImpl(ScopeLevel scope, SetComputationInfo info) {
		child.initializeParse(scope, info);
	}

}
