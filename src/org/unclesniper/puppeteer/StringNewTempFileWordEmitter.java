package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class StringNewTempFileWordEmitter implements NewTempFileWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringNewTempFileWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	public void buildArgv(Machine machine, Consumer<String> sink) {
		for(String word : words)
			sink.accept(word);
	}

}
