package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("booleanVar")
public class BooleanVariable extends AbstractVariable {

	public BooleanVariable() {}

	public BooleanVariable(String name) {
		super(name);
	}

	private Boolean realGet(ScopeLevel scope) {
		try {
			return scope.getBoolean(this, false);
		}
		catch(UndefinedBooleanVariableException usve) {
			throw new Doom("Exception shouldn't have been thrown", usve);
		}
	}

	@Override
	public void printValue(ScopeLevel scope, StructSink sink) {
		if(!scope.hasBoolean(this)) {
			sink.print("<undefined>");
			return;
		}
		Boolean value = realGet(scope);
		if(value == null)
			sink.print("<null>");
		else
			sink.print(value ? "true" : "false");
	}

	@Override
	public ValueRestorer saveValue(ScopeLevel scope) {
		if(!scope.hasBoolean(this))
			return () -> scope.eraseBoolean(this);
		Boolean value = realGet(scope);
		return () -> scope.setBoolean(this, value, AssignmentScope.EXISTING);
	}

}
