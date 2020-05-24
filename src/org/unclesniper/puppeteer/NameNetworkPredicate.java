package org.unclesniper.puppeteer;

public class NameNetworkPredicate implements NetworkPredicate {

	private String name;

	public NameNetworkPredicate() {}

	public NameNetworkPredicate(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean test(NetworkStep.NetworkStepInfo info) {
		return name != null && name.equals(info.getNetwork().getName());
	}

}
