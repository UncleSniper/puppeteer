package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.Argument;
import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.AssignmentScope;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.MachinePredicate;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.MachinePredicateVariable;

@ShorthandName("setMachinePredicateVarSyntax")
public class SetMachinePredicateVariableSyntax extends ZeroWidthSyntax {

	private MachinePredicateVariable variable;

	private MachinePredicate value;

	public SetMachinePredicateVariableSyntax() {}

	public SetMachinePredicateVariableSyntax(MachinePredicateVariable variable, MachinePredicate value) {
		this.variable = variable;
		this.value = value;
	}

	protected SetMachinePredicateVariableSyntax(SetMachinePredicateVariableSyntax setVar) {
		super(setVar);
		variable = setVar.variable;
		value = setVar.value;
	}

	public MachinePredicateVariable getVariable() {
		return variable;
	}

	public void setVariable(MachinePredicateVariable variable) {
		this.variable = variable;
	}

	public MachinePredicate getValue() {
		return value;
	}

	public void setValue(MachinePredicate value) {
		this.value = value;
	}

	@Override
	protected Syntax duplicate() {
		return new SetMachinePredicateVariableSyntax(this);
	}

	@Override
	protected void initializeParseImpl(ScopeLevel scope, SetComputationInfo info) {
		if(variable instanceof Argument)
			((Argument)variable).initializeValue(scope);
	}

	@Override
	protected void seen(ScopeLevel scope) throws PuppetException {
		if(variable != null)
			scope.setMachinePredicate(variable, value, AssignmentScope.LOCAL);
	}

}
