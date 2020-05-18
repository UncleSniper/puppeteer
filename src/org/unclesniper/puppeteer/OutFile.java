package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public interface OutFile extends AutoCloseable {

	File asFile() throws PuppetException;

	OutputStream asStream() throws PuppetException;

	@Override
	void close() throws IOException, PuppetException;

}
