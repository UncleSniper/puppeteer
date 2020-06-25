package org.unclesniper.puppeteer.util;

public final class MachineProperties {

	public static final String MKTEMP_EXECUTABLE = "exe:mktemp";
	public static final String RM_EXECUTABLE = "exe:rm";
	public static final String SSH_EXECUTABLE = "exe:ssh";
	public static final String SCP_EXECUTABLE = "exe:scp";

	public static final String TEMP_DIRECTORY = "tempdir";

	public static final String SSH_IP_VERSION = "ssh:ipv";
	public static final String SSH_COMPRESS = "ssh:compress";
	public static final String SSH_CIPHERS = "ssh:ciphers";
	public static final String SSH_CONFIG_FILE = "ssh:configFile";
	public static final String SSH_IDENTITY_FILE = "ssh:identityFile";
	public static final String SSH_PORT = "ssh:port";
	public static final String SSH_REMOTE_USER = "ssh:remoteUser";
	public static final String SSH_REMOTE_HOST = "ssh:remoteHost";

	private MachineProperties() {}

}
