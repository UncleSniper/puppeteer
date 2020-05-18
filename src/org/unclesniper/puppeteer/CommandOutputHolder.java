package org.unclesniper.puppeteer;

public interface CommandOutputHolder {

	boolean hasStdoutLines();

	Iterable<String> getStdoutLines();

	boolean hasStderrLine();

	Iterable<String> getStderrLines();

}
