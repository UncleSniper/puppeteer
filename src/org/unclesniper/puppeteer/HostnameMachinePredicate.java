package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineIsNamed")
public class HostnameMachinePredicate extends AbstractMachinePredicate {

	private String hostname;

	public HostnameMachinePredicate() {}

	public HostnameMachinePredicate(String hostname) {
		this.hostname = hostname;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Override
	protected boolean testImpl(MachineStep.MachineStepInfo info) {
		return hostname != null && hostname.equals(info.getMachine().getHostname());
	}

	@Override
	public void printTo(StructSink sink) {
		StructPrintable.beginObject(this, sink, false);
		printHostnameMachinePredicateTo(sink, false);
		StructPrintable.endObject(sink);
	}

	protected void printHostnameMachinePredicateTo(StructSink sink, boolean more) {
		printAbstractMachinePredicateTo(sink, true);
		StructPrintable.string("hostname", hostname, sink);
		if(more)
			sink.print(",");
		sink.endl();
	}

}
