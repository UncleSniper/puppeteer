package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machinePredicateVar")
public class MachinePredicateVariable extends AbstractVariable {

	public MachinePredicateVariable() {}

	public MachinePredicateVariable(String name) {
		super(name);
	}

	private MachinePredicate realGet(ScopeLevel scope) {
		try {
			return scope.getMachinePredicate(this, false);
		}
		catch(UndefinedMachinePredicateVariableException unpve) {
			throw new Doom("Exception shouldn't have been thrown", unpve);
		}
	}

	@Override
	public void printValue(ScopeLevel scope, StructSink sink) {
		if(!scope.hasMachinePredicate(this)) {
			sink.print("<undefined>");
			return;
		}
		MachinePredicate value = realGet(scope);
		if(value == null)
			sink.print("<null>");
		else
			value.printTo(sink);
	}

	@Override
	public ValueRestorer saveValue(ScopeLevel scope) {
		if(!scope.hasMachinePredicate(this))
			return () -> scope.eraseMachinePredicate(this);
		MachinePredicate value = realGet(scope);
		return () -> scope.setMachinePredicate(this, value, AssignmentScope.EXISTING);
	}

}
