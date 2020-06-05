package org.unclesniper.puppeteer;

public interface MachineStepStringSource extends Traceable {

	void buildString(MachineStep.MachineStepInfo info, StringBuilder sink) throws PuppetException;

	public static String accumulate(Iterable<MachineStepStringSource> pieces, MachineStep.MachineStepInfo info)
			throws PuppetException {
		StringBuilder builder = new StringBuilder();
		for(MachineStepStringSource piece : pieces)
			piece.buildString(info, builder);
		return builder.toString();
	}

}
