package org.unclesniper.puppeteer;

public class LocalUtils {

	public static final boolean DEBUG_LOCAL;

	static {
		String value = System.getenv("PUPPETEER_DEBUG_LOCAL_ACTIONS");
		DEBUG_LOCAL = value != null && value.length() > 0;
	}

	private LocalUtils() {}

}
