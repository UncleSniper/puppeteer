package org.unclesniper.puppeteer;

import java.io.File;
import java.util.List;
import java.io.InputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.io.OutputStream;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class ExecCopySlave implements CopySlave {

	private Machine execHost;

	private TempArea tempArea;

	private final List<CopyToWordEmitter> toWords = new LinkedList<CopyToWordEmitter>();

	private final List<CopyFromWordEmitter> fromWords = new LinkedList<CopyFromWordEmitter>();

	public ExecCopySlave() {}

	public ExecCopySlave(Machine execHost) {
		this(execHost, null);
	}

	public ExecCopySlave(Machine execHost, TempArea tempArea) {
		this.execHost = execHost;
		this.tempArea = tempArea;
	}

	public Machine getExecHost() {
		return execHost;
	}

	public void setExecHost(Machine execHost) {
		this.execHost = execHost;
	}

	public TempArea getTempArea() {
		return tempArea;
	}

	public void setTempArea(TempArea tempArea) {
		this.tempArea = tempArea;
	}

	public void addToWord(CopyToWordEmitter word) {
		if(word != null)
			toWords.add(word);
	}

	public void addToWord(String word) {
		if(word == null)
			return;
		StringCopyToWordEmitter emitter = new StringCopyToWordEmitter();
		emitter.addWord(word);
		toWords.add(emitter);
	}

	public void addFromWord(CopyFromWordEmitter word) {
		if(word != null)
			fromWords.add(word);
	}

	public void addFromWord(String word) {
		if(word == null)
			return;
		StringCopyFromWordEmitter emitter = new StringCopyFromWordEmitter();
		emitter.addWord(word);
		fromWords.add(emitter);
	}

	private void copyTo(Machine machine, InFile source, String destination) throws PuppetException {
		try(InFile inf = source) {
			List<String> argv = new LinkedList<String>();
			Consumer<String> sink = argv::add;
			for(CopyToWordEmitter emitter : toWords)
				emitter.buildArgv(machine, source, destination, sink);
			copy(argv, ioe -> new UploadIOPuppetException(machine, destination, ioe));
		}
		catch(IOException ioe) {
			throw new UploadIOPuppetException(machine, destination, ioe);
		}
	}

	private void copyFrom(Machine machine, String source, OutFile destination) throws PuppetException {
		try(OutFile outf = destination) {
			List<String> argv = new LinkedList<String>();
			Consumer<String> sink = argv::add;
			for(CopyFromWordEmitter emitter : fromWords)
				emitter.buildArgv(machine, source, destination, sink);
			copy(argv, ioe -> new DownloadIOPuppetException(machine, source, ioe));
		}
		catch(IOException ioe) {
			throw new DownloadIOPuppetException(machine, source, ioe);
		}
	}

	private <ExceptT extends PuppetException & CommandOutputBuffer> void copy(Collection<String> argv,
			Function<? super IOException, ExceptT> onError) throws PuppetException {
		ExecControl xctl = ExecUtils.on(execHost).execute(execHost, argv, null, null, 0);
		ExecUtils.awaitSuccess(xctl, true, onError);
	}

	@Override
	public void copyTo(Machine machine, File source, String destination) throws PuppetException {
		copyTo(machine, new FileInFile(source), destination);
	}

	@Override
	public void copyTo(Machine machine, InputStream source, String destination) throws PuppetException {
		copyTo(machine, new StreamInFile(source, tempArea), destination);
	}

	@Override
	public void copyFrom(Machine machine, String source, File destination) throws PuppetException {
		copyFrom(machine, source, new FileOutFile(destination));
	}

	@Override
	public void copyFrom(Machine machine, String source, OutputStream destination) throws PuppetException {
		copyFrom(machine, source, new StreamOutFile(destination, tempArea));
	}

}
