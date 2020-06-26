package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.IdentityHashMap;
import java.util.function.Function;

public class ScopeLevel {

	private final ScopeLevel parent;

	private final Map<StringVariable, String> strings = new IdentityHashMap<StringVariable, String>();

	private final Map<NetworkPredicateVariable, NetworkPredicate> networkPredicates
			= new IdentityHashMap<NetworkPredicateVariable, NetworkPredicate>();

	private final Map<MachinePredicateVariable, MachinePredicate> machinePredicates
			= new IdentityHashMap<MachinePredicateVariable, MachinePredicate>();

	public ScopeLevel(ScopeLevel parent) {
		this.parent = parent;
	}

	public ScopeLevel getParent() {
		return parent;
	}

	private <VarT, ValueT> boolean hasX(VarT variable, Function<ScopeLevel, Map<VarT, ValueT>> field) {
		for(ScopeLevel level = this; level != null; level = level.parent) {
			if(field.apply(level).containsKey(variable))
				return true;
		}
		return false;
	}

	private <VarT, ValueT, ErrorT extends UndefinedVariableException> ValueT getX(VarT variable,
			boolean required, Function<ScopeLevel, Map<VarT, ValueT>> field, Function<VarT, ErrorT> error)
			throws ErrorT {
		for(ScopeLevel level = this; level != null; level = level.parent) {
			Map<VarT, ValueT> values = field.apply(level);
			if(values.containsKey(variable))
				return values.get(variable);
		}
		if(required)
			throw error.apply(variable);
		return null;
	}

	private <VarT, ValueT> void setX(VarT variable, ValueT value, AssignmentScope scope,
			Function<ScopeLevel, Map<VarT, ValueT>> field) {
		if(variable == null)
			return;
		switch(scope == null ? AssignmentScope.EXISTING : scope) {
			case GLOBAL:
				setXGlobal(variable, value, field);
				break;
			case EXISTING:
				setXExisting(variable, value, field);
				break;
			case LOCAL:
				field.apply(this).put(variable, value);
				break;
			default:
				throw new Doom("Unrecognized AssignmentScope: " + scope.name());
		}
	}

	private <VarT, ValueT> void setXGlobal(VarT variable, ValueT value,
			Function<ScopeLevel, Map<VarT, ValueT>> field) {
		ScopeLevel level = this;
		while(level.parent != null)
			level = level.parent;
		field.apply(level).put(variable, value);
	}

	private <VarT, ValueT> void setXExisting(VarT variable, ValueT value,
			Function<ScopeLevel, Map<VarT, ValueT>> field) {
		for(ScopeLevel level = this; level != null; level = level.parent) {
			Map<VarT, ValueT> values = field.apply(level);
			if(values.containsKey(variable)) {
				values.put(variable, value);
				return;
			}
		}
		field.apply(this).put(variable, value);
	}

	public boolean hasString(StringVariable variable) {
		return hasX(variable, level -> level.strings);
	}

	public String getString(StringVariable variable, boolean required) throws UndefinedStringVariableException {
		return getX(variable, required, level -> level.strings, UndefinedStringVariableException::new);
	}

	public void setString(StringVariable variable, String value, AssignmentScope scope) {
		setX(variable, value, scope, level -> level.strings);
	}

	public boolean hasNetworkPredicate(NetworkPredicateVariable variable) {
		return hasX(variable, level -> level.networkPredicates);
	}

	public NetworkPredicate getNetworkPredicate(NetworkPredicateVariable variable, boolean required)
			throws UndefinedNetworkPredicateVariableException {
		return getX(variable, required, level -> level.networkPredicates,
				UndefinedNetworkPredicateVariableException::new);
	}

	public void setNetworkPredicate(NetworkPredicateVariable variable, NetworkPredicate value,
			AssignmentScope scope) {
		setX(variable, value, scope, level -> level.networkPredicates);
	}

	public boolean hasMachinePredicate(MachinePredicateVariable variable) {
		return hasX(variable, level -> level.machinePredicates);
	}

	public MachinePredicate getMachinePredicate(MachinePredicateVariable variable, boolean required)
			throws UndefinedMachinePredicateVariableException {
		return getX(variable, required, level -> level.machinePredicates,
				UndefinedMachinePredicateVariableException::new);
	}

	public void setMachinePredicate(MachinePredicateVariable variable, MachinePredicate value,
			AssignmentScope scope) {
		setX(variable, value, scope, level -> level.machinePredicates);
	}

}
