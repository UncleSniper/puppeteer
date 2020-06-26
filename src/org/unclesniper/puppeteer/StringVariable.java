package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stringVar")
public class StringVariable extends AbstractVariable {

	public StringVariable() {}

	public StringVariable(String name) {
		super(name);
	}

	private String realGet(ScopeLevel scope) {
		try {
			return scope.getString(this, false);
		}
		catch(UndefinedStringVariableException usve) {
			throw new Doom("Exception shouldn't have been thrown", usve);
		}
	}

	@Override
	public void printValue(ScopeLevel scope, StructSink sink) {
		if(!scope.hasString(this)) {
			sink.print("<undefined>");
			return;
		}
		String value = realGet(scope);
		if(value != null)
			sink.print(value);
	}

	@Override
	public ValueRestorer saveValue(ScopeLevel scope) {
		if(!scope.hasString(this))
			return () -> scope.eraseString(this);
		String value = realGet(scope);
		return () -> scope.setString(this, value, AssignmentScope.EXISTING);
	}

}
