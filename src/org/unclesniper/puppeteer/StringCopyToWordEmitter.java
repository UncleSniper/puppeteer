package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("copyToWord")
public class StringCopyToWordEmitter extends AbstractCopyToWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringCopyToWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	protected void buildArgvImpl(Machine machine, InFile source, String destination, Consumer<String> sink)
			throws PuppetException {
		for(String word : words)
			sink.accept(word);
	}

}
