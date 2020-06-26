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

	@Override
	public void printTo(StructSink sink) {
		StructPrintable.beginObject(this, sink, false);
		printTagNetworkPredicateTo(sink, false);
		StructPrintable.endObject(sink);
	}

	protected void printTagNetworkPredicateTo(StructSink sink, boolean more) {
		printAbstractTagPredicateTo(sink, more);
	}

}
