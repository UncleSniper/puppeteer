package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("fileByExec")
public class ExecFileSlave extends AbstractFileSlave {

	private Machine execHost;

	private final List<NewTempFileWordEmitter> tempFileWords = new LinkedList<NewTempFileWordEmitter>();

	private final List<DeleteFileWordEmitter> deleteFileWords = new LinkedList<DeleteFileWordEmitter>();

	public ExecFileSlave() {}

	public ExecFileSlave(Machine execHost) {
		this.execHost = execHost;
	}

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
		emitter.ingestObjectDefinitionLocation(this);
		emitter.addWord(word);
		tempFileWords.add(emitter);
	}

	public boolean hasTempFileWords() {
		return !tempFileWords.isEmpty();
	}

	public void addDeleteFileWord(DeleteFileWordEmitter word) {
		if(word != null)
			deleteFileWords.add(word);
	}

	public void addDeleteFileWord(String word) {
		if(word == null)
			return;
		StringDeleteFileWordEmitter emitter = new StringDeleteFileWordEmitter();
		emitter.ingestObjectDefinitionLocation(this);
		emitter.addWord(word);
		deleteFileWords.add(emitter);
	}

	public boolean hasDeleteFileWords() {
		return !deleteFileWords.isEmpty();
	}

	@Override
	protected String newTempFileImpl(Machine machine) throws PuppetException {
		List<String> argv = new LinkedList<String>();
		Consumer<String> sink = argv::add;
		NewTempFileInfo info = new NewTempFileInfo(machine, execHost);
		for(NewTempFileWordEmitter emitter : tempFileWords)
			emitter.buildArgv(info, sink);
		ExecControl xctl = ExecUtils.on(execHost).execute(execHost, argv, null, null, 0);
		ExecUtils.CapturedOutput output = ExecUtils.awaitSuccess(xctl, true,
				ioe -> new NewTempFileIOPuppetException(machine, ioe));
		if(output.stdout.size() != 1)
			throw new MktempDidNotPrintSingleLineException(machine, output.stdout.size());
		return output.stdout.get(0);
	}

	@Override
	protected void deleteFileImpl(Machine machine, String file) throws PuppetException {
		List<String> argv = new LinkedList<String>();
		Consumer<String> sink = argv::add;
		DeleteFileInfo info = new DeleteFileInfo(machine, execHost, file);
		for(DeleteFileWordEmitter emitter : deleteFileWords)
			emitter.buildArgv(info, sink);
		ExecControl xctl = ExecUtils.on(execHost).execute(execHost, argv, null, null, 0);
		ExecUtils.awaitSuccess(xctl, true, ioe -> new DeleteFileIOPuppetException(machine, file, ioe));
	}

}
