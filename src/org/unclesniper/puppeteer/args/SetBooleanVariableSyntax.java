package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.Argument;
import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.BooleanVariable;
import org.unclesniper.puppeteer.AssignmentScope;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("setBooleanVarSyntax")
public class SetBooleanVariableSyntax extends ZeroWidthSyntax {

	private BooleanVariable variable;

	private Boolean value;

	public SetBooleanVariableSyntax() {}

	public SetBooleanVariableSyntax(BooleanVariable variable, Boolean value) {
		this.variable = variable;
		this.value = value;
	}

	protected SetBooleanVariableSyntax(SetBooleanVariableSyntax setVar) {
		super(setVar);
		variable = setVar.variable;
		value = setVar.value;
	}

	public BooleanVariable getVariable() {
		return variable;
	}

	public void setVariable(BooleanVariable variable) {
		this.variable = variable;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	@Override
	protected Syntax duplicate() {
		return new SetBooleanVariableSyntax(this);
	}

	@Override
	protected void initializeParseImpl(ScopeLevel scope, SetComputationInfo info) {
		if(variable instanceof Argument)
			((Argument)variable).initializeValue(scope);
	}

	@Override
	protected void seen(ScopeLevel scope) throws PuppetException {
		if(variable != null)
			scope.setBoolean(variable, value, AssignmentScope.LOCAL);
	}

}
