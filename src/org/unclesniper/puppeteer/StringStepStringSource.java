package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepString")
public class StringStepStringSource extends AbstractStepStringSource {

	private String string;

	public StringStepStringSource() {}

	public StringStepStringSource(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	protected void buildStringImpl(Step.StepInfo info, StringBuilder sink) throws PuppetException {
		if(string != null)
			sink.append(string);
	}

}
