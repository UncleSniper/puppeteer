package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineIs")
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
		try {
			return isSatisfied(predicates, info, MachinePredicate::test);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public void printTo(StructSink sink) {
		StructPrintable.beginObject(this, sink, false);
		printJunctorMachinePredicateTo(sink, false);
		StructPrintable.endObject(sink);
	}

	protected void printJunctorMachinePredicateTo(StructSink sink, boolean more) {
		printAbstractJunctorPredicateTo(sink, true);
		StructPrintable.list("predicate", predicates, sink);
		if(more)
			sink.print(",");
		sink.endl();
	}

}
