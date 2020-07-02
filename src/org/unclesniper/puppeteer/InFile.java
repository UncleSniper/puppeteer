package org.unclesniper.puppeteer;

import java.io.Closeable;
import java.io.InputStream;

public interface InFile extends Closeable {

	String asFile() throws PuppetException;

	InputStream asStream() throws PuppetException;

	void copyTo(MachineStep.MachineStepInfo stepInfo, Machine machine, String destination) throws PuppetException;

}
