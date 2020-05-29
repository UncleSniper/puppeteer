package org.unclesniper.puppeteer;

import java.util.Deque;
import java.util.LinkedList;

public class PuppetException extends Exception {

	private final Deque<Traceable> trace = new LinkedList<Traceable>();

	public PuppetException(String message) {
		super(message);
	}

	public PuppetException(String message, Throwable cause) {
		super(message, cause);
	}

	public void addPuppetFrame(Traceable frame) {
		if(frame != null)
			trace.addLast(frame);
	}

	public boolean hasPuppetFrames() {
		return !trace.isEmpty();
	}

	public Iterable<Traceable> getPuppetFrames() {
		return trace;
	}

}
