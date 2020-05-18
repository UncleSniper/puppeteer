package org.unclesniper.puppeteer;

import java.io.IOException;
import java.util.function.Consumer;
import java.io.InterruptedIOException;

public class LineReaderThread extends Thread {

	private LineReadEnd source;

	private Consumer<String> sink;

	private IOException error;

	public LineReaderThread(LineReadEnd source, Consumer<String> sink) {
		this.source = source;
		this.sink = sink;
	}

	public LineReadEnd getSource() {
		return source;
	}

	public void setSource(LineReadEnd source) {
		this.source = source;
	}

	public Consumer<String> getSink() {
		return sink;
	}

	public void setSink(Consumer<String> sink) {
		this.sink = sink;
	}

	public IOException drain() {
		try {
			join();
		}
		catch(InterruptedException ie) {
			return new InterruptedIOException(ie.getMessage());
		}
		return error;
	}

	@Override
	public void run() {
		try(LineReadEnd lre = source) {
			for(;;) {
				String line = source.readLine();
				if(line == null)
					break;
				sink.accept(line);
			}
		}
		catch(IOException ioe) {
			error = ioe;
		}
	}

}
