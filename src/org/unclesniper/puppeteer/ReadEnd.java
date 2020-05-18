package org.unclesniper.puppeteer;

import java.io.Closeable;
import java.io.IOException;

public interface ReadEnd extends Closeable {

	int read(byte[] b, int off, int len) throws IOException;

}
