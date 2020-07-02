package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Collection;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("execByExec")
public class ExecExecSlave extends AbstractExecSlave {

	private Machine execHost;

	private final List<ExecWordEmitter> words = new LinkedList<ExecWordEmitter>();

	public ExecExecSlave() {}

	public ExecExecSlave(Machine execHost) {
		this.execHost = execHost;
	}

	public Machine getExecHost() {
		return execHost;
	}

	public void setExecHost(Machine execHost) {
		this.execHost = execHost;
	}

	public void addWord(ExecWordEmitter word) {
		if(word != null)
			words.add(word);
	}

	public void addWord(String word) {
		if(word == null)
			return;
		StringExecWordEmitter emitter = new StringExecWordEmitter();
		emitter.ingestObjectDefinitionLocation(this);
		emitter.addWord(word);
		words.add(emitter);
	}

	public boolean hasWords() {
		return !words.isEmpty();
	}

	protected ExecControl execute(Machine machine, Argv argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException {
		List<String> subArgv = new LinkedList<String>();
		Consumer<String> sink = subArgv::add;
		ExecInfo info = new ExecInfo(machine, execHost, argv, workdir, environ, flags);
		for(ExecWordEmitter emitter : words)
			emitter.buildArgv(info, sink);
		return ExecUtils.on(execHost).execute(execHost, subArgv, null, null, flags);
	}

	@Override
	protected ExecControl executeImpl(Machine machine, Collection<String> argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException {
		return execute(machine, new CollectionArgv(argv), workdir, environ, flags);
	}

	@Override
	protected ExecControl executeImpl(Machine machine, String[] argv, String workdir, Map<String, String> environ,
			int flags) throws PuppetException {
		return execute(machine, new ArrayArgv(argv), workdir, environ, flags);
	}

}
