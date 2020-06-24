package org.unclesniper.puppeteer;

public interface StderrCapture extends Traceable {

	PuppetIORunnable captureStderr(MachineStep.MachineStepInfo info, ExecControl ctl) throws PuppetException;

}
