package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.IdentityHashMap;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("world")
public class World {

	private class NetworkRenameListener implements NetworkListener {

		NetworkRenameListener() {}

		@Override
		public void networkNameChanged(Network network, String oldName) throws AmbiguousNetworkNameException {
			if(!allNetworks.containsKey(network))
				return;
			String newName = network.getName();
			if(Objects.equals(oldName, newName))
				return;
			if(newName != null) {
				Network other = networks.get(newName);
				if(other != null && other != network)
					throw new AmbiguousNetworkNameException(newName);
				networks.put(newName, network);
			}
			if(oldName != null && networks.get(oldName) == network)
				networks.remove(oldName);
		}

	}

	private final Map<String, Network> networks = new HashMap<String, Network>();

	private final Map<Network, Network> allNetworks = new IdentityHashMap<Network, Network>();

	private final NetworkRenameListener networkRenameListener = new NetworkRenameListener();

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
		network.addNetworkListener(networkRenameListener);
	}

}
