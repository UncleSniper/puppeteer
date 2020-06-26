package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineHasTag")
public class TagMachinePredicate extends AbstractTagPredicate implements MachinePredicate {

	public TagMachinePredicate() {}

	@Override
	public boolean test(MachineStep.MachineStepInfo info) throws PuppetException {
		try {
			return isSatisfied(info.getMachine()::hasTag);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	@Override
	public void printTo(StructSink sink) {
		StructPrintable.beginObject(this, sink, false);
		printTagMachinePredicateTo(sink, false);
		StructPrintable.endObject(sink);
	}

	protected void printTagMachinePredicateTo(StructSink sink, boolean more) {
		printAbstractTagPredicateTo(sink, more);
	}

}
