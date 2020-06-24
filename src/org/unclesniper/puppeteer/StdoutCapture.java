package org.unclesniper.puppeteer;

public interface StdoutCapture extends Traceable {

	PuppetIORunnable captureStdout(MachineStep.MachineStepInfo info, ExecControl ctl) throws PuppetException;

}
