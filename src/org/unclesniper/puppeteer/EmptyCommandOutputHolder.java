package org.unclesniper.puppeteer;

import java.util.Collections;

public class EmptyCommandOutputHolder implements CommandOutputHolder {

	public static final EmptyCommandOutputHolder instance = new EmptyCommandOutputHolder();

	private static final Iterable<String> EMPTY_LIST = Collections.emptyList();

	public EmptyCommandOutputHolder() {}

	@Override
	public boolean hasStdoutLines() {
		return false;
	}

	@Override
	public Iterable<String> getStdoutLines() {
		return EmptyCommandOutputHolder.EMPTY_LIST;
	}

	@Override
	public boolean hasStderrLine() {
		return false;
	}

	@Override
	public Iterable<String> getStderrLines() {
		return EmptyCommandOutputHolder.EMPTY_LIST;
	}

}
