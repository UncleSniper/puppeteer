package org.unclesniper.puppeteer;

import java.io.Closeable;
import java.io.IOException;

public interface CharReadEnd extends Closeable {

	int read(char[] cbuf, int off, int len) throws IOException;

}
