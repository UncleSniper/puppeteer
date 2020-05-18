package org.unclesniper.puppeteer;

import java.io.Closeable;
import java.io.IOException;

public interface WriteEnd extends Closeable {

	void write(byte[] b, int off, int len) throws IOException;

}
