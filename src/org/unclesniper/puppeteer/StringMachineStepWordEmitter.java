package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class StringMachineStepWordEmitter implements MachineStepWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringMachineStepWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	public void buildArgv(MachineStep.MachineStepInfo info, Consumer<String> sink) {
		for(String word : words)
			sink.accept(word);
	}

}
