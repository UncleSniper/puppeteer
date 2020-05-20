package org.unclesniper.puppeteer;

public class FileUtils {

	public static class FileHolder implements AutoCloseable {

		private final Machine machine;

		private String file;

		public FileHolder(Machine machine, String file) {
			this.machine = machine;
			this.file = file;
		}

		public String getFile() {
			return file;
		}

		public void release() {
			file = null;
		}

		@Override
		public void close() throws PuppetException {
			if(file != null) {
				machine.getFileSlave().deleteFile(machine, file);
				file = null;
			}
		}

	}

	private FileUtils() {}

	public static FileHolder withFile(Machine machine, String file) {
		return new FileHolder(machine, file);
	}

	public static FileHolder withTempFile(Machine machine) throws PuppetException {
		return new FileHolder(machine, machine.getFileSlave().newTempFile(machine));
	}

}
