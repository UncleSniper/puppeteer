package org.unclesniper.puppeteer;

public class TagNetworkPredicate extends AbstractTagPredicate implements NetworkPredicate {

	public TagNetworkPredicate() {}

	@Override
	public boolean test(NetworkStep.NetworkStepInfo info) throws PuppetException {
		return isSatisfied(info.getNetwork()::hasTag);
	}

}
