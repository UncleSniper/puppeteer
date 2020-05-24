package org.unclesniper.puppeteer;

public interface MachinePredicate {

	boolean test(MachineStep.MachineStepInfo info) throws PuppetException;

}
