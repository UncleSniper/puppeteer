package org.unclesniper.puppeteer;

import java.io.Writer;
import java.io.IOException;

public class WriterCharWriteEnd implements CharWriteEnd {

	private Writer writer;

	public WriterCharWriteEnd(Writer writer) {
		this.writer = writer;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		writer.write(cbuf, off, len);
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}

}
