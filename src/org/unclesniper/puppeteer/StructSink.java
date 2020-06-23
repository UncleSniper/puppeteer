package org.unclesniper.puppeteer;

public interface StructSink {

	void print(String s);

	void endl();

	void indent();

	void unindent();

	default void println(String s) {
		print(s);
		endl();
	}

}
