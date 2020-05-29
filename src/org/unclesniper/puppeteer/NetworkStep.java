package org.unclesniper.puppeteer;

public interface NetworkStep extends GeneralStep {

	public class NetworkStepInfo extends Step.StepInfo {

		private Network network;

		public NetworkStepInfo(Step.StepInfo info) {
			this(info, null);
		}

		public NetworkStepInfo(Step.StepInfo info, Network network) {
			super(info);
			this.network = network;
		}

		public NetworkStepInfo(NetworkStepInfo info) {
			super(info);
			network = info.network;
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
