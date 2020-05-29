package org.unclesniper.puppeteer;

public class AliasMachinePredicate extends AbstractMachinePredicate {

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
	protected boolean testImpl(MachineStep.MachineStepInfo info) {
		return alias != null && info.getMachine().hasAlias(alias);
	}

}
