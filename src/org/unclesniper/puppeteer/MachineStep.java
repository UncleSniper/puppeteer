package org.unclesniper.puppeteer;

public interface MachineStep extends GeneralStep {

	public class MachineStepInfo extends NetworkStep.NetworkStepInfo {

		private Machine machine;

		public MachineStepInfo(PuppeteerUI ui, World world, Network network) {
			this(ui, world, network, null);
		}

		public MachineStepInfo(PuppeteerUI ui, World world, Network network, Machine machine) {
			super(ui, world, network);
			this.machine = machine;
		}

		public MachineStepInfo(NetworkStep.NetworkStepInfo info) {
			this(info, null);
		}

		public MachineStepInfo(NetworkStep.NetworkStepInfo info, Machine machine) {
			this(info == null ? null : info.getUI(), info == null ? null : info.getWorld(),
					info == null ? null : info.getNetwork(), machine);
		}

		public Machine getMachine() {
			return machine;
		}

		public void setMachine(Machine machine) {
			this.machine = machine;
		}

	}

	void perform(MachineStepInfo info) throws PuppetException;

}
