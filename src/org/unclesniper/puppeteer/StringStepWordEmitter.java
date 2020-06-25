package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepWord")
public class StringStepWordEmitter extends AbstractStepWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringStepWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	protected void buildArgvImpl(Step.StepInfo info, Consumer<String> sink) throws PuppetException {
		for(String word : words)
			sink.accept(word);
	}

}
