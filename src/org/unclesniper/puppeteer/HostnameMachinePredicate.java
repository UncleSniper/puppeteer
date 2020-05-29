package org.unclesniper.puppeteer;

public class HostnameMachinePredicate extends AbstractMachinePredicate {

	private String hostname;

	public HostnameMachinePredicate() {}

	public HostnameMachinePredicate(String hostname) {
		this.hostname = hostname;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Override
	protected boolean testImpl(MachineStep.MachineStepInfo info) {
		return hostname != null && hostname.equals(info.getMachine().getHostname());
	}

}
