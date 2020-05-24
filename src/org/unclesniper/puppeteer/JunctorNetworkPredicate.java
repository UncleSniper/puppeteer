package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

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
		return isSatisfied(predicates, info, NetworkPredicate::test);
	}

}
