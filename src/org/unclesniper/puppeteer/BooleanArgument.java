package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.HashMap;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("booleanArg")
public class BooleanArgument extends BooleanVariable implements Argument {

	private static final Map<String, Boolean> NAMES;

	static {
		NAMES = new HashMap<String, Boolean>();
		NAMES.put("true", Boolean.TRUE);
		NAMES.put("false", Boolean.FALSE);
		NAMES.put("yes", Boolean.TRUE);
		NAMES.put("no", Boolean.FALSE);
		NAMES.put("on", Boolean.TRUE);
		NAMES.put("off", Boolean.FALSE);
	}

	private Boolean initialValue;

	public BooleanArgument() {}

	public BooleanArgument(String name) {
		super(name);
	}

	public Boolean getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(Boolean initialValue) {
		this.initialValue = initialValue;
	}

	@Override
	public void initializeValue(ScopeLevel level) {
		level.setBoolean(this, initialValue, AssignmentScope.LOCAL);
	}

	@Override
	public void addValue(ScopeLevel level, String specifier, String source) throws PuppetException {
		level.setBoolean(this, BooleanArgument.parseBoolean(specifier, source), AssignmentScope.LOCAL);
	}

	public static boolean parseBoolean(String specifier, String source) throws InvalidBooleanException {
		Boolean value = BooleanArgument.NAMES.get(specifier.toLowerCase());
		if(value == null)
			throw new InvalidBooleanException(specifier, source);
		return value;
	}

}
