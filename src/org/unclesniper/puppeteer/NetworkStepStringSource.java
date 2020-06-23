package org.unclesniper.puppeteer;

public interface NetworkStepStringSource extends Traceable {

	void buildString(NetworkStep.NetworkStepInfo info, StringBuilder sink) throws PuppetException;

	public static String accumulate(Iterable<NetworkStepStringSource> pieces, NetworkStep.NetworkStepInfo info)
			throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(NetworkStepStringSource piece : pieces)
			piece.buildString(info, builder);
		return builder.toString();
	}

}
