package org.unclesniper.puppeteer;

public interface MachinePredicate extends Traceable {

	boolean test(MachineStep.MachineStepInfo info) throws PuppetException;

}
