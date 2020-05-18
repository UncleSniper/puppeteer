package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public class ListCommandOutputBuffer implements CommandOutputBuffer, CommandOutputHolder {

	private final List<String> stdout = new LinkedList<String>();

	private final List<String> stderr = new LinkedList<String>();

	public ListCommandOutputBuffer() {}

	@Override
	public void addStdoutLine(String line) {
		if(line != null)
			stdout.add(line);
	}

	@Override
	public void addStderrLine(String line) {
		if(line != null)
			stderr.add(line);
	}

	@Override
	public boolean hasStdoutLines() {
		return !stdout.isEmpty();
	}

	@Override
	public Iterable<String> getStdoutLines() {
		return stdout;
	}

	@Override
	public boolean hasStderrLine() {
		return !stderr.isEmpty();
	}

	@Override
	public Iterable<String> getStderrLines() {
		return stderr;
	}

}
