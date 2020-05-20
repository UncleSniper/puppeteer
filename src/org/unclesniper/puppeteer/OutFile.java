package org.unclesniper.puppeteer;

import java.io.IOException;
import java.io.OutputStream;

public interface OutFile extends AutoCloseable {

	String asFile() throws PuppetException;

	OutputStream asStream() throws PuppetException;

	void copyFrom(Machine machine, String source) throws PuppetException;

	@Override
	void close() throws IOException, PuppetException;

}
