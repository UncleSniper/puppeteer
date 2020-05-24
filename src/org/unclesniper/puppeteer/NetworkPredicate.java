package org.unclesniper.puppeteer;

public interface NetworkPredicate {

	boolean test(NetworkStep.NetworkStepInfo info) throws PuppetException;

}
