package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.IdentityHashMap;

public class Network {

	private String name;

	private final Map<String, Machine> machines = new HashMap<String, Machine>();

	private final Map<Machine, Machine> allMachines = new IdentityHashMap<Machine, Machine>();

	public Network() {}

	public Network(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	}

	public Iterable<Machine> getMachines() {
		return allMachines.keySet();
	}

	public static String makeMessage(Network network, String ifPresent, String ifAbsent) {
		String name = network == null ? null : network.getName();
		return name == null ? ifAbsent : ifPresent + '\'' + name + '\'';
	}

}
