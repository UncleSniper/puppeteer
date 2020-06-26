package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("booleanVar")
public class BooleanVariable extends AbstractVariable {

	public BooleanVariable() {}

	public BooleanVariable(String name) {
		super(name);
	}

	@Override
	public void printValue(ScopeLevel scope, StructSink sink) {
		if(!scope.hasBoolean(this)) {
			sink.print("<undefined>");
			return;
		}
		Boolean value;
		try {
			value = scope.getBoolean(this, false);
		}
		catch(UndefinedBooleanVariableException usve) {
			throw new Doom("Exception shouldn't have been thrown", usve);
		}
		if(value == null)
			sink.print("<null>");
		else
			sink.print(value ? "true" : "false");
	}

}
