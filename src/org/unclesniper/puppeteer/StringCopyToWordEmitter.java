package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class StringCopyToWordEmitter implements CopyToWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringCopyToWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	public void buildArgv(Machine machine, InFile source, String destination, Consumer<String> sink)
			throws PuppetException {
		for(String word : words)
			sink.accept(word);
	}

}
