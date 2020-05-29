package org.unclesniper.puppeteer;

public class NameNetworkPredicate extends AbstractNetworkPredicate {

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
	protected boolean testImpl(NetworkStep.NetworkStepInfo info) {
		return name != null && name.equals(info.getNetwork().getName());
	}

}
