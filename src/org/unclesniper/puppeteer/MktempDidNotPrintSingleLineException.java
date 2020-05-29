package org.unclesniper.puppeteer;

public class MktempDidNotPrintSingleLineException extends PuppetException {

	private final Machine machine;

	private final int lineCount;

	public MktempDidNotPrintSingleLineException(Machine machine, int lineCount) {
		super("Failed to create temporary file" + Machine.makeMessage(machine, " on ", "")
				+ Traceable.makeLocation(machine, " (machine defined at ", ")", "") + ": Command printed "
				+ lineCount + " lines instead of exactly one");
		this.machine = machine;
		this.lineCount = lineCount;
	}

	public Machine getMachine() {
		return machine;
	}

	public int getLineCount() {
		return lineCount;
	}

}
