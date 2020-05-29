package org.unclesniper.puppeteer;

public abstract class AbstractJunctorPredicate extends AbstractTraceable {

	private Junctor junctor;

	private boolean negate;

	public AbstractJunctorPredicate() {}

	public Junctor getJunctor() {
		return junctor;
	}

	public void setJunctor(Junctor junctor) {
		this.junctor = junctor;
	}

	public boolean isNegate() {
		return negate;
	}

	public void setNegate(boolean negate) {
		this.negate = negate;
	}

	protected <PredicateT, ArgumentT> boolean isSatisfied(Iterable<PredicateT> predicates, ArgumentT argument,
			PuppetBiPredicate<PredicateT, ArgumentT> test) throws PuppetException {
		boolean shortCircuit = Junctor.getShortCircuit(junctor);
		for(PredicateT predicate : predicates) {
			if(test.test(predicate, argument) == shortCircuit)
				return shortCircuit != negate;
		}
		return shortCircuit == negate;
	}

}
