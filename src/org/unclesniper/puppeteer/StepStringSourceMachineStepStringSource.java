package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepStringMachineStepString")
public class StepStringSourceMachineStepStringSource extends AbstractMachineStepStringSource {

	private StepStringSource source;

	public StepStringSourceMachineStepStringSource() {}

	public StepStringSourceMachineStepStringSource(StepStringSource source) {
		this.source = source;
	}

	public StepStringSource getSource() {
		return source;
	}

	public void setSource(StepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(MachineStep.MachineStepInfo info, StringBuilder sink) throws PuppetException {
		if(source != null)
			source.buildString(info, sink);
	}

}
