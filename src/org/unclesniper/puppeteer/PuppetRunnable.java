package org.unclesniper.puppeteer;

public interface PuppetRunnable extends PuppetIORunnable {

	public class TryJoin implements AutoCloseable {

		private final PuppetRunnable task;

		public TryJoin(PuppetRunnable task) {
			this.task = task;
		}

		@Override
		public void close() throws PuppetException {
			if(task != null)
				task.runTask();
		}

	}

	@Override
	void runTask() throws PuppetException;

	public static TryJoin tryJoin(PuppetRunnable task) {
		return new TryJoin(task);
	}

}
