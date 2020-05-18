package org.unclesniper.puppeteer;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamWriteEnd implements WriteEnd {

	private OutputStream stream;

	public OutputStreamWriteEnd(OutputStream stream) {
		this.stream = stream;
	}

	public OutputStream getStream() {
		return stream;
	}

	public void setStream(OutputStream stream) {
		this.stream = stream;
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		stream.write(b, off, len);
	}

	@Override
	public void close() throws IOException {
		stream.close();
	}

}
