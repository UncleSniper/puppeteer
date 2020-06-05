package org.unclesniper.puppeteer;

import java.io.IOException;

public interface PuppetIORunnable {

	public class TryJoin implements AutoCloseable {

		private final PuppetIORunnable task;

		public TryJoin(PuppetIORunnable task) {
			this.task = task;
		}

		@Override
		public void close() throws PuppetException, IOException {
			if(task != null)
				task.runTask();
		}

	}

	void runTask() throws PuppetException, IOException;

	public static TryJoin tryJoin(PuppetIORunnable task) {
		return new TryJoin(task);
	}

}
