package org.unclesniper.puppeteer;

public interface MachineStringCapture extends Traceable {

	void captureString(MachineStep.MachineStepInfo info, String value) throws PuppetException;

}
