package org.unclesniper.puppeteer;

public interface CopyInFileProvider extends Traceable {

	void copyTo(CopySlave slave, MachineStep.MachineStepInfo info, String destination) throws PuppetException;

}
