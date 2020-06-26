package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkPredicateVar")
public class NetworkPredicateVariable extends AbstractVariable {

	public NetworkPredicateVariable() {}

	public NetworkPredicateVariable(String name) {
		super(name);
	}

	private NetworkPredicate realGet(ScopeLevel scope) {
		try {
			return scope.getNetworkPredicate(this, false);
		}
		catch(UndefinedNetworkPredicateVariableException unpve) {
			throw new Doom("Exception shouldn't have been thrown", unpve);
		}
	}

	@Override
	public void printValue(ScopeLevel scope, StructSink sink) {
		if(!scope.hasNetworkPredicate(this)) {
			sink.print("<undefined>");
			return;
		}
		NetworkPredicate value = realGet(scope);
		if(value == null)
			sink.print("<null>");
		else
			value.printTo(sink);
	}

	@Override
	public ValueRestorer saveValue(ScopeLevel scope) {
		if(!scope.hasNetworkPredicate(this))
			return () -> scope.eraseNetworkPredicate(this);
		NetworkPredicate value = realGet(scope);
		return () -> scope.setNetworkPredicate(this, value, AssignmentScope.EXISTING);
	}

}
