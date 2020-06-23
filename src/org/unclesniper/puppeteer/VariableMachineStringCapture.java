package org.unclesniper.puppeteer;

public class VariableMachineStringCapture extends AbstractMachineStringCapture {

	private StringVariable variable;

	private AssignmentScope scope;

	public VariableMachineStringCapture() {}

	public VariableMachineStringCapture(StringVariable variable) {
		this(variable, null);
	}

	public VariableMachineStringCapture(StringVariable variable, AssignmentScope scope) {
		this.variable = variable;
		this.scope = scope;
	}

	public StringVariable getVariable() {
		return variable;
	}

	public void setVariable(StringVariable variable) {
		this.variable = variable;
	}

	public AssignmentScope getScope() {
		return scope;
	}

	public void setScope(AssignmentScope scope) {
		this.scope = scope;
	}

	@Override
	protected void captureStringImpl(MachineStep.MachineStepInfo info, String value) {
		if(variable != null)
			info.getScope().setString(variable, value, scope);
	}

}
