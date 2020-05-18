package org.unclesniper.puppeteer;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface CopySlave {

	void copyTo(Machine machine, File source, String destination) throws PuppetException;

	void copyTo(Machine machine, InputStream source, String destination) throws PuppetException;

	void copyFrom(Machine machine, String source, File destination) throws PuppetException;

	void copyFrom(Machine machine, String source, OutputStream destination) throws PuppetException;

}
