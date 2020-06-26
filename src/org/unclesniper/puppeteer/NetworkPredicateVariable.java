package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkPredicateVar")
public class NetworkPredicateVariable extends AbstractVariable {

	public NetworkPredicateVariable() {}

	public NetworkPredicateVariable(String name) {
		super(name);
	}

	@Override
	public void printValue(ScopeLevel scope, StructSink sink) {
		if(!scope.hasNetworkPredicate(this)) {
			sink.print("<undefined>");
			return;
		}
		NetworkPredicate value;
		try {
			value = scope.getNetworkPredicate(this, false);
		}
		catch(UndefinedNetworkPredicateVariableException unpve) {
			throw new Doom("Exception shouldn't have been thrown", unpve);
		}
		if(value == null)
			sink.print("<null>");
		else
			value.printTo(sink);
	}

}
