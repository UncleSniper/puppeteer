package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("copyFromWord")
public class StringCopyFromWordEmitter extends AbstractCopyFromWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringCopyFromWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	protected void buildArgvImpl(CopySlave.CopyFromInfo info, Consumer<String> sink)
			throws PuppetException {
		for(String word : words)
			sink.accept(word);
	}

}
