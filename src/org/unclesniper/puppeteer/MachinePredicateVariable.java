package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machinePredicateVar")
public class MachinePredicateVariable extends AbstractVariable {

	public MachinePredicateVariable() {}

	public MachinePredicateVariable(String name) {
		super(name);
	}

	@Override
	public void printValue(ScopeLevel scope, StructSink sink) {
		if(!scope.hasMachinePredicate(this)) {
			sink.print("<undefined>");
			return;
		}
		MachinePredicate value;
		try {
			value = scope.getMachinePredicate(this, false);
		}
		catch(UndefinedMachinePredicateVariableException unpve) {
			throw new Doom("Exception shouldn't have been thrown", unpve);
		}
		if(value == null)
			sink.print("<null>");
		else
			value.printTo(sink);
	}

}
