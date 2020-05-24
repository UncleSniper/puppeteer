package org.unclesniper.puppeteer;

public class HostnameMachinePredicate implements MachinePredicate {

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
	public boolean test(MachineStep.MachineStepInfo info) {
		return hostname != null && hostname.equals(info.getMachine().getHostname());
	}

}
