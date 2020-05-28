package org.unclesniper.puppeteer;

public interface MachineStepStringSource {

	void buildString(MachineStep.MachineStepInfo info, StringBuilder sink) throws PuppetException;

}
