package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkIs")
public class JunctorNetworkPredicate extends AbstractJunctorPredicate implements NetworkPredicate {

	private final List<NetworkPredicate> predicates = new LinkedList<NetworkPredicate>();

	public JunctorNetworkPredicate() {}

	public Iterable<NetworkPredicate> getPredicates() {
		return predicates;
	}

	public void addPredicate(NetworkPredicate predicate) {
		if(predicate != null)
			predicates.add(predicate);
	}

	@Override
	public boolean test(NetworkStep.NetworkStepInfo info) throws PuppetException {
		try {
			return isSatisfied(predicates, info, NetworkPredicate::test);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public void printTo(StructSink sink) {
		StructPrintable.beginObject(this, sink, false);
		printJunctorNetworkPredicateTo(sink, false);
		StructPrintable.endObject(sink);
	}

	protected void printJunctorNetworkPredicateTo(StructSink sink, boolean more) {
		printAbstractJunctorPredicateTo(sink, true);
		StructPrintable.list("predicate", predicates, sink);
		if(more)
			sink.print(",");
		sink.endl();
	}

}
