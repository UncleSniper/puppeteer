package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class StringDeleteFileWordEmitter extends AbstractDeleteFileWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringDeleteFileWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	protected void buildArgvImpl(Machine machine, String file, Consumer<String> sink) throws PuppetException {
		for(String word : words)
			sink.accept(word);
	}

}
