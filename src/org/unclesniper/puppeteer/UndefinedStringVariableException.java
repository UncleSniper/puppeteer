package org.unclesniper.puppeteer;

public class UndefinedStringVariableException extends UndefinedVariableException {

	private final StringVariable variable;

	public UndefinedStringVariableException(StringVariable variable) {
		super("Undefined string variable" + Variable.makeMessage(variable, " ", ""));
		this.variable = variable;
	}

	@Override
	public StringVariable getVariable() {
		return variable;
	}

}
