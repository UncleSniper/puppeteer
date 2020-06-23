package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.IdentityHashMap;

public class ScopeLevel {

	private final ScopeLevel parent;

	private final Map<StringVariable, String> strings = new IdentityHashMap<StringVariable, String>();

	public ScopeLevel(ScopeLevel parent) {
		this.parent = parent;
	}

	public ScopeLevel getParent() {
		return parent;
	}

	public boolean hasString(StringVariable variable) {
		for(ScopeLevel level = this; level != null; level = level.parent) {
			if(level.strings.containsKey(variable))
				return true;
		}
		return false;
	}

	public String getString(StringVariable variable, boolean required) throws UndefinedStringVariableException {
		for(ScopeLevel level = this; level != null; level = level.parent) {
			if(level.strings.containsKey(variable))
				return level.strings.get(variable);
		}
		if(required)
			throw new UndefinedStringVariableException(variable);
		return null;
	}

	public void setString(StringVariable variable, String value, AssignmentScope scope) {
		if(variable == null)
			return;
		switch(scope == null ? AssignmentScope.EXISTING : scope) {
			case GLOBAL:
				setStringGlobal(variable, value);
				break;
			case EXISTING:
				setStringExisting(variable, value);
				break;
			case LOCAL:
				strings.put(variable, value);
				break;
			default:
				throw new Doom("Unrecognized AssignmentScope: " + scope.name());
		}
	}

	private void setStringGlobal(StringVariable variable, String value) {
		ScopeLevel level = this;
		while(level.parent != null)
			level = level.parent;
		level.strings.put(variable, value);
	}

	private void setStringExisting(StringVariable variable, String value) {
		for(ScopeLevel level = this; level != null; level = level.parent) {
			if(level.strings.containsKey(variable)) {
				level.strings.put(variable, value);
				return;
			}
		}
		strings.put(variable, value);
	}

}
