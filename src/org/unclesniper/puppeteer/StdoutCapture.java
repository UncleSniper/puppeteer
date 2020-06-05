package org.unclesniper.puppeteer;

public interface StdoutCapture extends Traceable {

	PuppetRunnable captureStdout(MachineStep.MachineStepInfo info, ExecControl ctl) throws PuppetException;

}
