package org.unclesniper.puppeteer.util;

public final class StringUtils {

	private StringUtils() {}

	public static String repeatChar(char c, int count) {
		if(count <= 0)
			return "";
		String s = StringUtils.repeatChar(c, count / 2);
		if(count % 2 == 0)
			return s + s;
		return s + c + s;
	}

}
