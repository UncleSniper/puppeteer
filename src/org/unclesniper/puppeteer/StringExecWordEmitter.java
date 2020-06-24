package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("execWord")
public class StringExecWordEmitter extends AbstractExecWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringExecWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	protected void buildArgvImpl(ExecSlave.ExecInfo info, Consumer<String> sink) {
		for(String word : words)
			sink.accept(word);
	}

}
