package org.unclesniper.puppeteer;

public class UndefinedBooleanVariableException extends UndefinedVariableException {

	private final BooleanVariable variable;

	public UndefinedBooleanVariableException(BooleanVariable variable) {
		super("Undefined boolean variable" + Variable.makeMessage(variable, " ", "")
				+ Traceable.makeLocation(variable, " (variable defined at ", ")", ""));
		this.variable = variable;
	}

	@Override
	public BooleanVariable getVariable() {
		return variable;
	}

}
