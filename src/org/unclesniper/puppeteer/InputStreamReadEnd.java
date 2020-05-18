package org.unclesniper.puppeteer;

import java.io.InputStream;
import java.io.IOException;

public class InputStreamReadEnd implements ReadEnd {

	private InputStream stream;

	public InputStreamReadEnd(InputStream stream) {
		this.stream = stream;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return stream.read(b, off, len);
	}

	@Override
	public void close() throws IOException {
		stream.close();
	}

}
