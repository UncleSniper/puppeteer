package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkHasTag")
public class TagNetworkPredicate extends AbstractTagPredicate implements NetworkPredicate {

	public TagNetworkPredicate() {}

	@Override
	public boolean test(NetworkStep.NetworkStepInfo info) throws PuppetException {
		try {
			return isSatisfied(info.getNetwork()::hasTag);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
