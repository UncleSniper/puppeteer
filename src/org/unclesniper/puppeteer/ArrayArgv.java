package org.unclesniper.puppeteer;

import java.util.List;
import java.util.ArrayList;

public class ArrayArgv implements Argv {

	private final String[] argv;

	private List<String> list;

	public ArrayArgv(String[] argv) {
		this.argv = argv;
	}

	public String[] asArray() {
		return argv;
	}

	public int getSize() {
		return argv.length;
	}

	private List<String> getList() {
		if(list == null) {
			list = new ArrayList<String>();
			for(String word : argv)
				list.add(word);
		}
		return list;
	}

	public Iterable<String> asIterable() {
		return getList();
	}

	public List<String> asList() {
		return getList();
	}

}
