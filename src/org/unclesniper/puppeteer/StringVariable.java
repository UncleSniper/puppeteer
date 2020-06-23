package org.unclesniper.puppeteer;

public class StringVariable extends AbstractVariable {

	public StringVariable() {}

	public StringVariable(String name) {
		super(name);
	}

	@Override
	public void printValue(ScopeLevel scope, StructSink sink) {
		if(!scope.hasString(this)) {
			sink.print("<undefined>");
			return;
		}
		String value;
		try {
			value = scope.getString(this, false);
		}
		catch(UndefinedStringVariableException usve) {
			throw new Doom("Exception shouldn't have been thrown", usve);
		}
		if(value != null)
			sink.print(value);
	}

}
