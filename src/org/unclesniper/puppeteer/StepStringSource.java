package org.unclesniper.puppeteer;

public interface StepStringSource extends Traceable {

	void buildString(Step.StepInfo info, StringBuilder sink) throws PuppetException;

	public static String accumulate(Iterable<StepStringSource> pieces, Step.StepInfo info) throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(StepStringSource piece : pieces)
			piece.buildString(info, builder);
		return builder.toString();
	}

}
