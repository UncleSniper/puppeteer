package org.unclesniper.puppeteer;

public interface Step extends GeneralStep {

	public class StepInfo {

		private final PuppeteerUI ui;

		private final World world;

		private final ScopeLevel scope;

		public StepInfo(PuppeteerUI ui, World world, ScopeLevel scope) {
			this.ui = ui;
			this.world = world;
			this.scope = scope;
		}

		public StepInfo(StepInfo info) {
			ui = info.ui;
			world = info.world;
			scope = info.scope;
		}

		public PuppeteerUI getUI() {
			return ui;
		}

		public World getWorld() {
			return world;
		}

		public ScopeLevel getScope() {
			return scope;
		}

	}

	void perform(StepInfo info) throws PuppetException;

}
