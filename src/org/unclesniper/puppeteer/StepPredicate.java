package org.unclesniper.puppeteer;

public interface StepPredicate extends Traceable, StructPrintable {

	boolean test(Step.StepInfo info) throws PuppetException;

}
