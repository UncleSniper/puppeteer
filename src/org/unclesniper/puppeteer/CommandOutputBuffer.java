package org.unclesniper.puppeteer;

public interface CommandOutputBuffer {

	void addStdoutLine(String line);

	void addStderrLine(String line);

}
