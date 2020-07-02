package org.unclesniper.puppeteer;

public interface FileSlave extends Traceable {

	public final class NewTempFileInfo {

		public final MachineStep.MachineStepInfo stepInfo;

		public final Machine machine;

		public final Machine execHost;

		public NewTempFileInfo(MachineStep.MachineStepInfo stepInfo, Machine machine, Machine execHost) {
			this.stepInfo = stepInfo;
			this.machine = machine;
			this.execHost = execHost;
		}

	}

	public final class DeleteFileInfo {

		public final MachineStep.MachineStepInfo stepInfo;

		public final Machine machine;

		public final Machine execHost;

		public final String file;

		public DeleteFileInfo(MachineStep.MachineStepInfo stepInfo, Machine machine, Machine execHost,
				String file) {
			this.stepInfo = stepInfo;
			this.machine = machine;
			this.execHost = execHost;
			this.file = file;
		}

	}

	String newTempFile(MachineStep.MachineStepInfo stepInfo, Machine machine) throws PuppetException;

	void deleteFile(MachineStep.MachineStepInfo stepInfo, Machine machine, String file) throws PuppetException;

}
