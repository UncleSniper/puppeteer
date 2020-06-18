package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.PuppetException;

public class ArgumentSyntaxException extends PuppetException {

	private final String expected;

	private final String found;

	private final String location;

	public ArgumentSyntaxException(String expected, String found, String location) {
		super("Expected " + expected + (location == null ? "" : " at " + location)
				+ ", but found " + (found == null ? "end of arguments" : '\'' + found + '\''));
		this.expected = expected;
		this.found = found;
		this.location = location;
	}

	public String getExpected() {
		return expected;
	}

	public String getFound() {
		return found;
	}

	public String getLocation() {
		return location;
	}

	public static String expect(Iterable<String> literals, boolean end) {
		StringBuilder builder = new StringBuilder();
		String pending = null;
		int have = 0;
		if(literals != null) {
			for(String literal : literals) {
				if(literal == null)
					continue;
				if(pending != null) {
					if(have > 0)
						builder.append(", ");
					builder.append('\'');
					builder.append(pending);
					builder.append('\'');
					++have;
				}
				pending = literal;
			}
		}
		if(end) {
			if(pending != null) {
				if(have > 0)
					builder.append(", ");
				builder.append('\'');
				builder.append(pending);
				builder.append('\'');
				++have;
			}
			if(have > 0)
				builder.append(have > 1 ? ", or " : " or ");
			builder.append("end of arguments");
			++have;
		}
		else if(pending != null) {
			if(have > 0)
				builder.append(have > 1 ? ", or " : " or ");
			builder.append('\'');
			builder.append(pending);
			builder.append('\'');
			++have;
		}
		return have > 0 ? builder.toString() : "<empty language>";
	}

}
