package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machineStepWord")
public class StringMachineStepWordEmitter extends AbstractMachineStepWordEmitter {

	private final List<String> words = new LinkedList<String>();

	public StringMachineStepWordEmitter() {}

	public void addWord(String word) {
		if(word != null)
			words.add(word);
	}

	@Override
	protected void buildArgvImpl(MachineStep.MachineStepInfo info, Consumer<String> sink) {
		for(String word : words)
			sink.accept(word);
	}

}
