package org.unclesniper.puppeteer;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class CollectionArgv implements Argv {

	private final Collection<String> argv;

	private String[] array;

	private List<String> list;

	public CollectionArgv(Collection<String> argv) {
		this.argv = argv;
	}

	public Collection<String> getArgv() {
		return argv;
	}

	public String[] asArray() {
		if(array == null)
			array = argv.toArray(new String[argv.size()]);
		return array;
	}

	public int getSize() {
		return argv.size();
	}

	public Iterable<String> asIterable() {
		return argv;
	}

	public List<String> asList() {
		if(list == null) {
			list = new ArrayList<String>(argv.size());
			list.addAll(argv);
		}
		return list;
	}

}
