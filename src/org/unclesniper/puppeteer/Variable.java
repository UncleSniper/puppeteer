package org.unclesniper.puppeteer;

public interface Variable extends Traceable {

	public interface ValueRestorer extends AutoCloseable {

		@Override
		void close();

	}

	String getName();

	void printValue(ScopeLevel scope, StructSink sink);

	ValueRestorer saveValue(ScopeLevel scope);

	public static String makeMessage(Variable variable, String ifPresent, String ifAbsent) {
		if(variable == null)
			return ifAbsent;
		String name = variable.getName();
		return name == null ? ifAbsent : ifPresent + '\'' + name + '\'';
	}

}
