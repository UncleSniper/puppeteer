package org.unclesniper.puppeteer;

public class FileUtils {

	public static class FileHolder implements AutoCloseable {

		private final MachineStep.MachineStepInfo stepInfo;

		private final Machine machine;

		private String file;

		public FileHolder(MachineStep.MachineStepInfo stepInfo, Machine machine, String file) {
			this.stepInfo = stepInfo;
			this.machine = machine;
			this.file = file;
		}

		public MachineStep.MachineStepInfo getStepInfo() {
			return stepInfo;
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
				machine.getFileSlave().deleteFile(stepInfo, machine, file);
				file = null;
			}
		}

	}

	private FileUtils() {}

	public static FileHolder withFile(MachineStep.MachineStepInfo stepInfo, Machine machine, String file) {
		return new FileHolder(stepInfo, machine, file);
	}

	public static FileHolder withTempFile(MachineStep.MachineStepInfo stepInfo, Machine machine)
			throws PuppetException {
		return new FileHolder(stepInfo, machine, machine.getFileSlave().newTempFile(stepInfo, machine));
	}

}
