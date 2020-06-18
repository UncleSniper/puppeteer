package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.PuppetException;

public class LL1ityException extends PuppetException {

	private final Syntax emitter;

	public LL1ityException(Syntax emitter, String message) {
		super(message);
		this.emitter = emitter;
	}

	public Syntax getEmitter() {
		return emitter;
	}

}
