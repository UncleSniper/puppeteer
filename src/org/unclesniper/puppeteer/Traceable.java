package org.unclesniper.puppeteer;

public interface Traceable {

	String getTraceObjectDefinitionLocation();

	public static String makeLocation(Traceable traceable, String ifPresentHead, String ifPresentTail,
			String ifAbsent) {
		if(traceable == null)
			return ifAbsent;
		String location = traceable.getTraceObjectDefinitionLocation();
		return location == null ? ifAbsent : ifPresentHead + location + ifPresentTail;
	}

}
