package org.unclesniper.puppeteer;

public interface StderrCapture extends Traceable {

	PuppetRunnable captureStderr(MachineStep.MachineStepInfo info, ExecControl ctl) throws PuppetException;

}
