package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.Argument;
import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.AssignmentScope;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.NetworkPredicate;
import org.unclesniper.puppeteer.util.ShorthandName;
import org.unclesniper.puppeteer.NetworkPredicateVariable;

@ShorthandName("setNetworkPredicateVarSyntax")
public class SetNetworkPredicateVariableSyntax extends ZeroWidthSyntax {

	private NetworkPredicateVariable variable;

	private NetworkPredicate value;

	public SetNetworkPredicateVariableSyntax() {}

	public SetNetworkPredicateVariableSyntax(NetworkPredicateVariable variable, NetworkPredicate value) {
		this.variable = variable;
		this.value = value;
	}

	protected SetNetworkPredicateVariableSyntax(SetNetworkPredicateVariableSyntax setVar) {
		super(setVar);
		variable = setVar.variable;
		value = setVar.value;
	}

	public NetworkPredicateVariable getVariable() {
		return variable;
	}

	public void setVariable(NetworkPredicateVariable variable) {
		this.variable = variable;
	}

	public NetworkPredicate getValue() {
		return value;
	}

	public void setValue(NetworkPredicate value) {
		this.value = value;
	}

	@Override
	protected Syntax duplicate() {
		return new SetNetworkPredicateVariableSyntax(this);
	}

	@Override
	protected void initializeParseImpl(ScopeLevel scope, SetComputationInfo info) {
		if(variable instanceof Argument)
			((Argument)variable).initializeValue(scope);
	}

	@Override
	protected void seen(ScopeLevel scope) throws PuppetException {
		if(variable != null)
			scope.setNetworkPredicate(variable, value, AssignmentScope.LOCAL);
	}

}
