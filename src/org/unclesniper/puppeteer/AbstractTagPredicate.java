package org.unclesniper.puppeteer;

import java.util.Set;
import java.util.HashSet;
import java.util.function.Predicate;

public abstract class AbstractTagPredicate extends AbstractTraceable {

	private Junctor junctor;

	private final Set<String> tags = new HashSet<String>();

	public AbstractTagPredicate() {}

	public Junctor getJunctor() {
		return junctor;
	}

	public void setJunctor(Junctor junctor) {
		this.junctor = junctor;
	}

	public Iterable<String> getTags() {
		return tags;
	}

	public void addTag(String tag) {
		if(tag != null)
			tags.add(tag);
	}

	protected boolean isSatisfied(PuppetPredicate<String> hasTag) throws PuppetException {
		boolean shortCircuit = Junctor.getShortCircuit(junctor);
		for(String tag : tags) {
			if(hasTag.test(tag) == shortCircuit)
				return shortCircuit;
		}
		return !shortCircuit;
	}

	protected void printAbstractTagPredicateTo(StructSink sink, boolean more) {
		StructPrintable.enumConstant("junctor", junctor, sink);
		sink.println(",");
		StructPrintable.stringList("tag", tags, sink);
		if(more)
			sink.print(",");
		sink.endl();
	}

}
