package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

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

	public static ValueRestorer saveAllValues(Iterable<Variable> variables, ScopeLevel scope) {
		List<ValueRestorer> restorers = new LinkedList<ValueRestorer>();
		if(variables != null) {
			for(Variable variable : variables) {
				if(variable != null)
					restorers.add(variable.saveValue(scope));
			}
		}
		return () -> {
			for(ValueRestorer restorer : restorers)
				restorer.close();
		};
	}

}
