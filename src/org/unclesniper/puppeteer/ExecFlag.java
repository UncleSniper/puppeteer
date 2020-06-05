package org.unclesniper.puppeteer;

public enum ExecFlag {

	STDIN_UTF8(ExecSlave.FL_STDIN_UTF8),
	STDOUT_UTF8(ExecSlave.FL_STDOUT_UTF8),
	STDERR_UTF8(ExecSlave.FL_STDERR_UTF8);

	private final int flag;

	private ExecFlag(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

}
