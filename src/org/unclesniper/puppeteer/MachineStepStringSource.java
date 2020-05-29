package org.unclesniper.puppeteer;

public interface MachineStepStringSource extends Traceable {

	void buildString(MachineStep.MachineStepInfo info, StringBuilder sink) throws PuppetException;

}
