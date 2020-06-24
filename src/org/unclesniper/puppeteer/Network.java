package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.LinkedList;
import java.util.IdentityHashMap;
import org.unclesniper.puppeteer.util.Listeners;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("network")
public class Network extends AbstractTraceable {

	private class MachineRenameListener implements MachineListener {

		MachineRenameListener() {}

		@Override
		public void machineHostnameChanged(Machine machine, String oldName) throws AmbiguousMachineNameException {
			if(!allMachines.containsKey(machine))
				return;
			String newName = machine.getHostname();
			if(Objects.equals(oldName, newName))
				return;
			if(newName != null)
				addMachineName(machine, newName);
			if(oldName != null && machines.get(oldName) == machine)
				machines.remove(oldName);
		}

		@Override
		public void machineAliasAdded(Machine machine, String alias) throws AmbiguousMachineNameException {
			if(alias == null)
				return;
			if(!allMachines.containsKey(machine))
				return;
			addMachineName(machine, alias);
		}

		@Override
		public void machineAliasRemoved(Machine machine, String alias) {
			if(alias == null)
				return;
			if(!allMachines.containsKey(machine))
				return;
			if(machines.get(alias) == machine)
				machines.remove(alias);
		}

	}

	private String name;

	private final Map<String, Machine> machines = new HashMap<String, Machine>();

	private final Map<Machine, Machine> allMachines = new IdentityHashMap<Machine, Machine>();

	private final Set<String> tags = new HashSet<String>();

	private final Listeners<NetworkListener, AmbiguousNetworkNameException> listeners
			= new Listeners<NetworkListener, AmbiguousNetworkNameException>();

	private final MachineRenameListener machineRenameListener = new MachineRenameListener();

	public Network() {}

	public Network(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws AmbiguousNetworkNameException {
		String oldName = this.name;
		this.name = name;
		try {
			listeners.fire(listener -> listener.networkNameChanged(this, oldName));
		}
		catch(AmbiguousNetworkNameException anne) {
			this.name = oldName;
			listeners.fire(listener -> listener.networkNameChanged(this, name));
			throw anne;
		}
	}

	public Machine getMachine(String name) {
		return machines.get(name);
	}

	private boolean addMachineName(Machine machine, String mname) throws AmbiguousMachineNameException {
		Machine other = machines.get(mname);
		if(other != null) {
			if(other == machine)
				return false;
			throw new AmbiguousMachineNameException(mname, this);
		}
		machines.put(mname, machine);
		return true;
	}

	public void addMachine(Machine machine) throws AmbiguousMachineNameException {
		if(machine == null)
			return;
		if(allMachines.containsKey(machine))
			return;
		List<String> names = new LinkedList<String>();
		String cname = machine.getHostname();
		boolean good = false;
		try {
			if(cname != null) {
				if(addMachineName(machine, cname))
					names.add(cname);
			}
			for(String mname : machine.getAliases()) {
				if(addMachineName(machine, mname))
					names.add(mname);
			}
			allMachines.put(machine, machine);
			good = true;
		}
		finally {
			if(!good) {
				for(String mname : names)
					machines.remove(mname);
			}
		}
		machine.addMachineListener(machineRenameListener);
	}

	public Iterable<Machine> getMachines() {
		return allMachines.keySet();
	}

	public Iterable<String> getTags() {
		return tags;
	}

	public void addTag(String tag) {
		if(tag != null)
			tags.add(tag);
	}

	public boolean hasTag(String tag) {
		return tag != null && tags.contains(tag);
	}

	public Iterable<NetworkListener> getNetworkListeners() {
		return listeners.getListeners();
	}

	public boolean addNetworkListener(NetworkListener listener) {
		return listeners.addListener(listener);
	}

	public boolean removeNetworkListener(NetworkListener listener) {
		return listeners.removeListener(listener);
	}

	public static String makeMessage(Network network, String ifPresent, String ifAbsent) {
		String name = network == null ? null : network.getName();
		return name == null ? ifAbsent : ifPresent + '\'' + name + '\'';
	}

}
