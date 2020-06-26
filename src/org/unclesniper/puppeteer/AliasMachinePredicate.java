package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineHasAlias")
public class AliasMachinePredicate extends AbstractMachinePredicate {

	private String alias;

	public AliasMachinePredicate() {}

	public AliasMachinePredicate(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	protected boolean testImpl(MachineStep.MachineStepInfo info) {
		return alias != null && info.getMachine().hasAlias(alias);
	}

	@Override
	public void printTo(StructSink sink) {
		StructPrintable.beginObject(this, sink, false);
		printAliasMachinePredicateTo(sink, false);
		StructPrintable.endObject(sink);
	}

	protected void printAliasMachinePredicateTo(StructSink sink, boolean more) {
		printAbstractMachinePredicateTo(sink, true);
		StructPrintable.string("alias", alias, sink);
		if(more)
			sink.print(",");
		sink.endl();
	}

}
