package org.unclesniper.puppeteer;

import java.io.PrintStream;

public class PrintStreamStructSink extends AbstractStructSink {

	private PrintStream stream;

	public PrintStreamStructSink(PrintStream stream) {
		this(stream, 0);
	}

	public PrintStreamStructSink(PrintStream stream, int level) {
		super(level);
		this.stream = stream;
	}

	public PrintStream getStream() {
		return stream;
	}

	public void setStream(PrintStream stream) {
		this.stream = stream;
	}

	@Override
	protected void printImpl(String s) {
		stream.print(s);
	}

	@Override
	protected void endlImpl() {
		stream.println();
	}

}
