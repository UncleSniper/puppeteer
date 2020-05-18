package org.unclesniper.puppeteer;

import java.io.File;
import java.io.Closeable;
import java.io.InputStream;

public interface InFile extends Closeable {

	File asFile() throws PuppetException;

	InputStream asStream() throws PuppetException;

}
