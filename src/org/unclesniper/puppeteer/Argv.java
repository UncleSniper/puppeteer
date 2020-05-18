package org.unclesniper.puppeteer;

import java.util.List;

public interface Argv {

	String[] asArray();

	int getSize();

	Iterable<String> asIterable();

	List<String> asList();

}
