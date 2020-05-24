package org.unclesniper.puppeteer;

public interface NetworkStep extends GeneralStep {

	public class NetworkStepInfo extends Step.StepInfo {

		private Network network;

		public NetworkStepInfo(PuppeteerUI ui, World world) {
			this(ui, world, null);
		}

		public NetworkStepInfo(PuppeteerUI ui, World world, Network network) {
			super(ui, world);
			this.network = network;
		}

		public NetworkStepInfo(Step.StepInfo info) {
			this(info, null);
		}

		public NetworkStepInfo(Step.StepInfo info, Network network) {
			this(info == null ? null : info.getUI(), info == null ? null : info.getWorld(), network);
		}

		public Network getNetwork() {
			return network;
		}

		public void setNetwork(Network network) {
			this.network = network;
		}

	}

	void perform(NetworkStepInfo info) throws PuppetException;

}
