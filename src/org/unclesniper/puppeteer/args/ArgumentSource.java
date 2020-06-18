package org.unclesniper.puppeteer.args;

public interface ArgumentSource {

	String current();

	void next();

	String location();

}
