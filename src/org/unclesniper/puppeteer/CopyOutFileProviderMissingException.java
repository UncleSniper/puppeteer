package org.unclesniper.puppeteer;

public class CopyOutFileProviderMissingException extends PuppetException {

	private final GeneralStep step;

	public CopyOutFileProviderMissingException(GeneralStep step) {
		super("CopyOutFileProvider is missing in step" + Traceable.makeLocation(step, " defined at ", "", ""));
		this.step = step;
	}

	public GeneralStep getStep() {
		return step;
	}

}
