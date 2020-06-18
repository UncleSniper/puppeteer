package org.unclesniper.puppeteer;

public class Doom extends Error {

	public Doom(String message) {
		super("Programmer fsck(8)ed up: " + message);
	}

	public Doom(String message, Throwable cause) {
		super("Programmer fsck(8)ed up: " + message, cause);
	}

}
