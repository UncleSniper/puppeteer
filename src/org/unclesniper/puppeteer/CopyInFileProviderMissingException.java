package org.unclesniper.puppeteer;

public class CopyInFileProviderMissingException extends PuppetException {

	private final GeneralStep step;

	public CopyInFileProviderMissingException(GeneralStep step) {
		super("CopyInFileProvider is missing in step" + Traceable.makeLocation(step, " defined at ", "", ""));
		this.step = step;
	}

	public GeneralStep getStep() {
		return step;
	}

}
