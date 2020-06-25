package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import org.unclesniper.puppeteer.util.Listeners;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machine")
public class Machine extends AbstractTraceable {

	private String hostname;

	private ExecSlave execSlave;

	private CopySlave copySlave;

	private FileSlave fileSlave;

	private final Map<String, String> stringProperties = new HashMap<String, String>();

	private final Map<String, Integer> intProperties = new HashMap<String, Integer>();

	private final Set<String> tags = new HashSet<String>();

	private final Set<String> aliases = new HashSet<String>();

	private final Listeners<MachineListener, AmbiguousMachineNameException> listeners
			= new Listeners<MachineListener, AmbiguousMachineNameException>();

	public Machine() {}

	public Machine(String hostname) {
		this(hostname, null, null, null);
	}

	public Machine(String hostname, ExecSlave execSlave, CopySlave copySlave, FileSlave fileSlave) {
		this.hostname = hostname;
		this.execSlave = execSlave;
		this.copySlave = copySlave;
		this.fileSlave = fileSlave;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) throws AmbiguousMachineNameException {
		String oldName = this.hostname;
		this.hostname = hostname;
		try {
			listeners.fire(listener -> listener.machineHostnameChanged(this, oldName));
		}
		catch(AmbiguousMachineNameException amne) {
			this.hostname = oldName;
			listeners.fire(listener -> listener.machineHostnameChanged(this, hostname));
			throw amne;
		}
	}

	protected void buildExecSlave() {}

	public ExecSlave getExecSlave() {
		if(execSlave == null)
			buildExecSlave();
		return execSlave;
	}

	public ExecSlave getExecSlave(boolean required) throws MissingExecSlaveException {
		if(execSlave == null)
			buildExecSlave();
		if(required && execSlave == null)
			throw new MissingExecSlaveException(this);
		return execSlave;
	}

	public void setExecSlave(ExecSlave execSlave) {
		this.execSlave = execSlave;
	}

	protected void buildCopySlave() {}

	public CopySlave getCopySlave() {
		if(copySlave == null)
			buildCopySlave();
		return copySlave;
	}

	public CopySlave getCopySlave(boolean required) throws MissingCopySlaveException {
		if(copySlave == null)
			buildCopySlave();
		if(required && copySlave == null)
			throw new MissingCopySlaveException(this);
		return copySlave;
	}

	public void setCopySlave(CopySlave copySlave) {
		this.copySlave = copySlave;
	}

	protected void buildFileSlave() {}

	public FileSlave getFileSlave() {
		if(fileSlave == null)
			buildFileSlave();
		return fileSlave;
	}

	public FileSlave getFileSlave(boolean required) throws MissingFileSlaveException {
		if(fileSlave == null)
			buildFileSlave();
		if(required && fileSlave == null)
			throw new MissingFileSlaveException(this);
		return fileSlave;
	}

	public void setFileSlave(FileSlave fileSlave) {
		this.fileSlave = fileSlave;
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

	public void setIntProperty(String key, Integer value) {
		if(key == null)
			return;
		if(value == null)
			intProperties.remove(key);
		else
			intProperties.put(key, value);
	}

	public Integer getIntProperty(String key) {
		return intProperties.get(key);
	}

	public Integer getIntProperty(String key, Integer fallback) {
		Integer value = intProperties.get(key);
		return value == null ? fallback : value;
	}

	public Iterable<String> getTags() {
		return tags;
	}

	public void addTag(String tag) {
		if(tag != null)
			tags.add(tag);
	}

	public boolean hasTag(String tag) {
		return tag != null && tags.contains(tag);
	}

	public Iterable<String> getAliases() {
		return aliases;
	}

	public boolean addAlias(String alias) throws AmbiguousMachineNameException {
		if(alias == null)
			return false;
		if(!aliases.add(alias))
			return false;
		try {
			listeners.fire(listener -> listener.machineAliasAdded(this, alias));
		}
		catch(AmbiguousMachineNameException amne) {
			aliases.remove(alias);
			listeners.fire(listener -> listener.machineAliasRemoved(this, alias));
			throw amne;
		}
		return true;
	}

	public boolean hasAlias(String alias) {
		return alias != null && aliases.contains(alias);
	}

	public Iterable<MachineListener> getMachineListeners() {
		return listeners.getListeners();
	}

	public boolean addMachineListener(MachineListener listener) {
		return listeners.addListener(listener);
	}

	public boolean removeMachineListener(MachineListener listener) {
		return listeners.removeListener(listener);
	}

	public static String makeMessage(Machine machine, String ifPresent, String ifAbsent) {
		String hostname = machine == null ? null : machine.getHostname();
		return hostname == null ? ifAbsent : ifPresent + '\'' + hostname + '\'';
	}

}
