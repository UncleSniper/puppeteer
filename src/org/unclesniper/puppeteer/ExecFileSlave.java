package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public class ExecFileSlave implements FileSlave {

	private Machine execHost;

	private final List<NewTempFileWordEmitter> tempFileWords = new LinkedList<NewTempFileWordEmitter>();

	private final List<DeleteFileWordEmitter> deleteFileWords = new LinkedList<DeleteFileWordEmitter>();

	public ExecFileSlave() {}

	public Machine getExecHost() {
		return execHost;
	}

	public void setExecHost(Machine execHost) {
		this.execHost = execHost;
	}

	public void addTempFileWord(NewTempFileWordEmitter word) {
		if(word != null)
			tempFileWords.add(word);
	}

	public void addTempFileWord(String word) {
		if(word == null)
			return;
		StringNewTempFileWordEmitter emitter = new StringNewTempFileWordEmitter();
		emitter.addWord(word);
		tempFileWords.add(emitter);
	}

	public void addDeleteFileWord(DeleteFileWordEmitter word) {
		if(word != null)
			deleteFileWords.add(word);
	}

	public void addDeleteFileWord(String word) {
		if(word == null)
			return;
		StringDeleteFileWordEmitter emitter = new StringDeleteFileWordEmitter();
		emitter.addWord(word);
		deleteFileWords.add(emitter);
	}

	@Override
	public String newTempFile(Machine machine) throws PuppetException {
		List<String> argv = new LinkedList<String>();
		Consumer<String> sink = argv::add;
		for(NewTempFileWordEmitter emitter : tempFileWords)
			emitter.buildArgv(machine, sink);
		ExecControl xctl = ExecUtils.on(execHost).execute(execHost, argv, null, null, 0);
		ExecUtils.CapturedOutput output = ExecUtils.awaitSuccess(xctl, true,
				ioe -> new NewTempFileIOPuppetException(machine, ioe));
		if(output.stdout.size() != 1)
			throw new MktempDidNotPrintSingleLineException(machine, output.stdout.size());
		return output.stdout.get(0);
	}

	@Override
	public void deleteFile(Machine machine, String file) throws PuppetException {
		List<String> argv = new LinkedList<String>();
		Consumer<String> sink = argv::add;
		for(DeleteFileWordEmitter emitter : deleteFileWords)
			emitter.buildArgv(machine, file, sink);
		ExecControl xctl = ExecUtils.on(execHost).execute(execHost, argv, null, null, 0);
		ExecUtils.awaitSuccess(xctl, true, ioe -> new DeleteFileIOPuppetException(machine, file, ioe));
	}

}
