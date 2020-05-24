package org.unclesniper.puppeteer;

public class NameMachinePredicate implements MachinePredicate {

	private String name;

	public NameMachinePredicate() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean test(MachineStep.MachineStepInfo info) {
		if(name == null)
			return false;
		Machine machine = info.getMachine();
		return name.equals(machine.getHostname()) && machine.hasAlias(name);
	}

}
