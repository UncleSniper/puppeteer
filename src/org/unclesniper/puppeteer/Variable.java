package org.unclesniper.puppeteer;

public interface Variable extends Traceable {

	String getName();

	public static String makeMessage(Variable variable, String ifPresent, String ifAbsent) {
		if(variable == null)
			return ifAbsent;
		String name = variable.getName();
		return name == null ? ifAbsent : ifPresent + '\'' + name + '\'';
	}

}
