package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class ExecFileSlave implements FileSlave {

	private Machine execHost;

	private final List<NewTempFileWordEmitter> words = new LinkedList<NewTempFileWordEmitter>();

	public ExecFileSlave() {}

	public Machine getExecHost() {
		return execHost;
	}

	public void setExecHost(Machine execHost) {
		this.execHost = execHost;
	}

	public void addWord(NewTempFileWordEmitter word) {
		if(word != null)
			words.add(word);
	}

	public void addWord(String word) {
		if(word == null)
			return;
		StringNewTempFileWordEmitter emitter = new StringNewTempFileWordEmitter();
		emitter.addWord(word);
		words.add(emitter);
	}

	@Override
	public String newTempFile(Machine machine) throws PuppetException {
		List<String> argv = new LinkedList<String>();
		Consumer<String> sink = argv::add;
		for(NewTempFileWordEmitter emitter : words)
			emitter.buildArgv(machine, sink);
		ExecControl xctl = ExecUtils.on(execHost).execute(execHost, argv, null, null, 0);
		ExecUtils.CapturedOutput output = ExecUtils.awaitSuccess(xctl, true,
				ioe -> new NewTempFileIOPuppetException(machine, ioe));
		if(output.stdout.size() != 1)
			throw new MktempDidNotPrintSingleLineException(machine, output.stdout.size());
		return output.stdout.get(0);
	}

}
