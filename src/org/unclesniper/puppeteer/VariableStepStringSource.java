package org.unclesniper.puppeteer;

public class VariableStepStringSource extends AbstractStepStringSource {

	private Variable variable;

	private int indentLevel;

	public VariableStepStringSource() {}

	public VariableStepStringSource(Variable variable) {
		this.variable = variable;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public int getIndentLevel() {
		return indentLevel;
	}

	public void setIndentLevel(int indentLevel) {
		this.indentLevel = indentLevel;
	}

	@Override
	protected void buildStringImpl(Step.StepInfo info, StringBuilder sink) throws PuppetException {
		if(variable != null)
			variable.printValue(info.getScope(), new StringStructSink(sink, indentLevel));
	}

}
