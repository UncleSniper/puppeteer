package org.unclesniper.puppeteer;

public interface StdinProvider extends Traceable {

	PuppetRunnable provideStdin(MachineStep.MachineStepInfo info, ExecControl ctl) throws PuppetException;

}
