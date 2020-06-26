package org.unclesniper.puppeteer;

public interface NetworkPredicate extends Traceable, StructPrintable {

	boolean test(NetworkStep.NetworkStepInfo info) throws PuppetException;

}
