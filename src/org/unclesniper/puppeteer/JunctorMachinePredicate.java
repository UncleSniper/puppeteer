package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public class JunctorMachinePredicate extends AbstractJunctorPredicate implements MachinePredicate {

	private final List<MachinePredicate> predicates = new LinkedList<MachinePredicate>();

	public JunctorMachinePredicate() {}

	public Iterable<MachinePredicate> getPredicates() {
		return predicates;
	}

	public void addPredicate(MachinePredicate predicate) {
		if(predicate != null)
			predicates.add(predicate);
	}

	@Override
	public boolean test(MachineStep.MachineStepInfo info) throws PuppetException {
		return isSatisfied(predicates, info, MachinePredicate::test);
	}

}
