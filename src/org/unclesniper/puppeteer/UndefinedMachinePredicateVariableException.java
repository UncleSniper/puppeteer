package org.unclesniper.puppeteer;

public class UndefinedMachinePredicateVariableException extends UndefinedVariableException {

	private final MachinePredicateVariable variable;

	public UndefinedMachinePredicateVariableException(MachinePredicateVariable variable) {
		super("Undefined machine predicate variable" + Variable.makeMessage(variable, " ", "")
				+ Traceable.makeLocation(variable, " (variable defined at ", ")", ""));
		this.variable = variable;
	}

	@Override
	public MachinePredicateVariable getVariable() {
		return variable;
	}

}
