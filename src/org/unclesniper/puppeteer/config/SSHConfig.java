package org.unclesniper.puppeteer.config;

import java.util.Map;
import org.unclesniper.puppeteer.Machine;
import org.unclesniper.puppeteer.Traceable;
import org.unclesniper.puppeteer.PuppetException;

public interface SSHConfig extends Traceable {

	IPVersion getInternetProtocolVersion(Machine machine, Machine execHost) throws PuppetException;

	Boolean isCompress(Machine machine, Machine execHost) throws PuppetException;

	Iterable<String> getCiphers(Machine machine, Machine execHost) throws PuppetException;

	String getConfigFile(Machine machine, Machine execHost) throws PuppetException;

	String getIdentityFile(Machine machine, Machine execHost) throws PuppetException;

	Iterable<Map.Entry<String, String>> getOptions(Machine machine, Machine execHost) throws PuppetException;

	int getPort(Machine machine, Machine execHost) throws PuppetException;

	String getRemoteUser(Machine machine, Machine execHost) throws PuppetException;

	String getRemoteHost(Machine machine, Machine execHost) throws PuppetException;

	String getExecHostSSHExecutable(Machine machine, Machine execHost) throws PuppetException;

	String getExecHostSCPExecutable(Machine machine, Machine execHost) throws PuppetException;

}
