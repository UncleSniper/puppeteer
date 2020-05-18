package org.unclesniper.puppeteer;

import java.io.Reader;
import java.io.IOException;

public class ReaderCharReadEnd implements CharReadEnd {

	private Reader reader;

	public ReaderCharReadEnd(Reader reader) {
		this.reader = reader;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return reader.read(cbuf, off, len);
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

}
