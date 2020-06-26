package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineHasName")
public class NameMachinePredicate extends AbstractMachinePredicate {

	private String name;

	public NameMachinePredicate() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected boolean testImpl(MachineStep.MachineStepInfo info) {
		if(name == null)
			return false;
		Machine machine = info.getMachine();
		return name.equals(machine.getHostname()) && machine.hasAlias(name);
	}

	@Override
	public void printTo(StructSink sink) {
		StructPrintable.beginObject(this, sink, false);
		printNameMachinePredicateTo(sink, false);
		StructPrintable.endObject(sink);
	}

	protected void printNameMachinePredicateTo(StructSink sink, boolean more) {
		printAbstractMachinePredicateTo(sink, true);
		StructPrintable.string("name", name, sink);
		if(more)
			sink.print(",");
		sink.endl();
	}

}
