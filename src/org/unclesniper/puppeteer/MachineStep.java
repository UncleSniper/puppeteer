package org.unclesniper.puppeteer;

public interface MachineStep extends GeneralStep {

	public class MachineStepInfo extends NetworkStep.NetworkStepInfo {

		private Machine machine;

		public MachineStepInfo(NetworkStep.NetworkStepInfo info) {
			this(info, null);
		}

		public MachineStepInfo(NetworkStep.NetworkStepInfo info, Machine machine) {
			super(info);
			this.machine = machine;
		}

		public MachineStepInfo(MachineStepInfo info) {
			super(info);
			machine = info.machine;
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
