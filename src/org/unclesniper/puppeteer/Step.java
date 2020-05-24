package org.unclesniper.puppeteer;

public interface Step extends GeneralStep {

	public class StepInfo {

		private final PuppeteerUI ui;

		private final World world;

		public StepInfo(PuppeteerUI ui, World world) {
			this.ui = ui;
			this.world = world;
		}

		public PuppeteerUI getUI() {
			return ui;
		}

		public World getWorld() {
			return world;
		}

	}

	void perform(StepInfo info) throws PuppetException;

}
