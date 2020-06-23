package org.unclesniper.puppeteer;

public interface Variable extends Traceable {

	String getName();

	void printValue(ScopeLevel scope, StructSink sink);

	public static String makeMessage(Variable variable, String ifPresent, String ifAbsent) {
		if(variable == null)
			return ifAbsent;
		String name = variable.getName();
		return name == null ? ifAbsent : ifPresent + '\'' + name + '\'';
	}

}
