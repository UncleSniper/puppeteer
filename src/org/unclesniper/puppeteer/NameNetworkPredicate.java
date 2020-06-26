package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkIsNamed")
public class NameNetworkPredicate extends AbstractNetworkPredicate {

	private String name;

	public NameNetworkPredicate() {}

	public NameNetworkPredicate(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected boolean testImpl(NetworkStep.NetworkStepInfo info) {
		return name != null && name.equals(info.getNetwork().getName());
	}

	@Override
	public void printTo(StructSink sink) {
		StructPrintable.beginObject(this, sink, false);
		printNameNetworkPredicateTo(sink, false);
		StructPrintable.endObject(sink);
	}

	protected void printNameNetworkPredicateTo(StructSink sink, boolean more) {
		printAbstractNetworkPredicateTo(sink, true);
		StructPrintable.string("name", name, sink);
		if(more)
			sink.print(",");
		sink.endl();
	}

}
