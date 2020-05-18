package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Machine {

	private String hostname;

	private ExecSlave execSlave;

	private CopySlave copySlave;

	private final Map<String, String> stringProperties = new HashMap<String, String>();

	private final Set<String> tags = new HashSet<String>();

	public Machine() {}

	public Machine(String hostname) {
		this(hostname, null, null);
	}

	public Machine(String hostname, ExecSlave execSlave, CopySlave copySlave) {
		this.hostname = hostname;
		this.execSlave = execSlave;
		this.copySlave = copySlave;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public ExecSlave getExecSlave() {
		return execSlave;
	}

	public void setExecSlave(ExecSlave execSlave) {
		this.execSlave = execSlave;
	}

	public CopySlave getCopySlave() {
		return copySlave;
	}

	public void setCopySlave(CopySlave copySlave) {
		this.copySlave = copySlave;
	}

	public void setStringProperty(String key, String value) {
		if(key == null)
			return;
		if(value == null)
			stringProperties.remove(key);
		else
			stringProperties.put(key, value);
	}

	public String getStringProperty(String key) {
		return stringProperties.get(key);
	}

	public String getStringProperty(String key, String fallback) {
		String value = stringProperties.get(key);
		return value == null ? fallback : value;
	}

	public void addTag(String tag) {
		if(tag != null)
			tags.add(tag);
	}

	public boolean hasTag(String tag) {
		return tag != null && tags.contains(tag);
	}

	public static String makeMessage(Machine machine, String ifPresent, String ifAbsent) {
		String hostname = machine == null ? null : machine.getHostname();
		return hostname == null ? ifAbsent : ifPresent + " '" + hostname + '\'';
	}

}
