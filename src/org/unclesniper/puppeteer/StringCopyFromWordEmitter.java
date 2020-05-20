package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class StringCopyFromWordEmitter implements CopyFromWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringCopyFromWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	public void buildArgv(Machine machine, String source, OutFile destination, Consumer<String> sink)
			throws PuppetException {
		for(String word : words)
			sink.accept(word);
	}

}