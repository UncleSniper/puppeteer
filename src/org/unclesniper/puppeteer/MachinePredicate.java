package org.unclesniper.puppeteer;

public interface MachinePredicate extends Traceable, StructPrintable {

	boolean test(MachineStep.MachineStepInfo info) throws PuppetException;

}
