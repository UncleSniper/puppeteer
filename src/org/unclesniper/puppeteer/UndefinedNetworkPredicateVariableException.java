package org.unclesniper.puppeteer;

public class UndefinedNetworkPredicateVariableException extends UndefinedVariableException {

	private final NetworkPredicateVariable variable;

	public UndefinedNetworkPredicateVariableException(NetworkPredicateVariable variable) {
		super("Undefined network predicate variable" + Variable.makeMessage(variable, " ", "")
				+ Traceable.makeLocation(variable, " (variable defined at ", ")", ""));
		this.variable = variable;
	}

	@Override
	public NetworkPredicateVariable getVariable() {
		return variable;
	}

}
