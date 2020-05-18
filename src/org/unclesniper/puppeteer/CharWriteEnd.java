package org.unclesniper.puppeteer;

import java.io.Closeable;
import java.io.IOException;

public interface CharWriteEnd extends Closeable {

	void write(char[] cbuf, int off, int len) throws IOException;

}
