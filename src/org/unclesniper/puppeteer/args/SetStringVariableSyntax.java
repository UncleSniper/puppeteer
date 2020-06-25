package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.Argument;
import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.StringVariable;
import org.unclesniper.puppeteer.AssignmentScope;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("setStringVarSyntax")
public class SetStringVariableSyntax extends ZeroWidthSyntax {

	private StringVariable variable;

	private String value;

	public SetStringVariableSyntax() {}

	public SetStringVariableSyntax(StringVariable variable, String value) {
		this.variable = variable;
		this.value = value;
	}

	protected SetStringVariableSyntax(SetStringVariableSyntax setVar) {
		super(setVar);
		variable = setVar.variable;
		value = setVar.value;
	}

	public StringVariable getVariable() {
		return variable;
	}

	public void setVariable(StringVariable variable) {
		this.variable = variable;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	protected Syntax duplicate() {
		return new SetStringVariableSyntax(this);
	}

	@Override
	protected void initializeParseImpl(ScopeLevel scope, SetComputationInfo info) {
		if(variable instanceof Argument)
			((Argument)variable).initializeValue(scope);
	}

	@Override
	protected void seen(ScopeLevel scope) throws PuppetException {
		if(variable != null)
			scope.setString(variable, value, AssignmentScope.LOCAL);
	}

}
