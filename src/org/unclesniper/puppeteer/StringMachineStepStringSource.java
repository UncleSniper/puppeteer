package org.unclesniper.puppeteer;

public class StringMachineStepStringSource extends AbstractMachineStepStringSource {

	private String string;

	public StringMachineStepStringSource() {}

	public StringMachineStepStringSource(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	protected void buildStringImpl(MachineStep.MachineStepInfo info, StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
