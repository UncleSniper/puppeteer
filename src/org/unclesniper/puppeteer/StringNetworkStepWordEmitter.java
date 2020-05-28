package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class StringNetworkStepWordEmitter implements NetworkStepWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringNetworkStepWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	public void buildArgv(NetworkStep.NetworkStepInfo info, Consumer<String> sink) {
		for(String word : words)
			sink.accept(word);
	}

}
