package org.unclesniper.puppeteer;

public interface MachineListener {

	void machineHostnameChanged(Machine machine, String oldName) throws AmbiguousMachineNameException;

	void machineAliasAdded(Machine machine, String alias) throws AmbiguousMachineNameException;

	void machineAliasRemoved(Machine machine, String alias);

}
