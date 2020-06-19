package org.unclesniper.puppeteer;

public interface NetworkListener {

	void networkNameChanged(Network network, String oldName) throws AmbiguousNetworkNameException;

}
