package org.unclesniper.puppeteer;

public class StringMachineStepStringSource implements MachineStepStringSource {

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
	public void buildString(MachineStep.MachineStepInfo info, StringBuilder sink) {
		if(string != null)
			sink.append(string);
	}

}
