package org.unclesniper.puppeteer;

public class AliasMachinePredicate implements MachinePredicate {

	private String alias;

	public AliasMachinePredicate() {}

	public AliasMachinePredicate(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public boolean test(MachineStep.MachineStepInfo info) {
		return alias != null && info.getMachine().hasAlias(alias);
	}

}
