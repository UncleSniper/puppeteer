package org.unclesniper.puppeteer;

public interface FileSlave extends Traceable {

	public final class NewTempFileInfo {

		public final Machine machine;

		public final Machine execHost;

		public NewTempFileInfo(Machine machine, Machine execHost) {
			this.machine = machine;
			this.execHost = execHost;
		}

	}

	public final class DeleteFileInfo {

		public final Machine machine;

		public final Machine execHost;

		public final String file;

		public DeleteFileInfo(Machine machine, Machine execHost, String file) {
			this.machine = machine;
			this.execHost = execHost;
			this.file = file;
		}

	}

	String newTempFile(Machine machine) throws PuppetException;

	void deleteFile(Machine machine, String file) throws PuppetException;

}
