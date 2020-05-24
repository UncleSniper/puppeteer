package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.HashMap;
import java.util.IdentityHashMap;

public class World {

	private final Map<String, Network> networks = new HashMap<String, Network>();

	private final Map<Network, Network> allNetworks = new IdentityHashMap<Network, Network>();

	public World() {}

	public Network getNetwork(String name) {
		return networks.get(name);
	}

	public Iterable<Network> getNetworks() {
		return allNetworks.keySet();
	}

	public void addNetwork(Network network) throws AmbiguousNetworkNameException {
		if(network == null)
			return;
		if(allNetworks.containsKey(network))
			return;
		String nname = network.getName();
		if(nname != null) {
			Network other =  networks.get(nname);
			if(other != null)
				throw new AmbiguousNetworkNameException(nname);
			networks.put(nname, network);
		}
		allNetworks.put(network, network);
	}

}
