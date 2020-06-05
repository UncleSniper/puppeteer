package org.unclesniper.puppeteer;

public interface CopyOutFileProvider extends Traceable {

	void copyFrom(CopySlave slave, MachineStep.MachineStepInfo info, String source) throws PuppetException;

}
