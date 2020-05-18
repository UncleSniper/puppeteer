package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class StringExecWordEmitter implements ExecWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringExecWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	public void buildArgv(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			Consumer<String> sink) throws PuppetException {
		for(String word : words)
			sink.accept(word);
	}

}
