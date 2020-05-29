package org.unclesniper.puppeteer;

public interface NetworkPredicate extends Traceable {

	boolean test(NetworkStep.NetworkStepInfo info) throws PuppetException;

}
