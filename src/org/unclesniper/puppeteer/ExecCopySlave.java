package org.unclesniper.puppeteer;

import java.util.List;
import java.io.InputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.io.OutputStream;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("copyByExec")
public class ExecCopySlave extends AbstractCopySlave {

	private Machine execHost;

	private FileSlave localFileSlave;

	private boolean explicitIntermediate;

	private final List<CopyToWordEmitter> toWords = new LinkedList<CopyToWordEmitter>();

	private final List<CopyFromWordEmitter> fromWords = new LinkedList<CopyFromWordEmitter>();

	public ExecCopySlave() {}

	public ExecCopySlave(Machine execHost) {
		this.execHost = execHost;
	}

	public Machine getExecHost() {
		return execHost;
	}

	public void setExecHost(Machine execHost) {
		this.execHost = execHost;
	}

	public FileSlave getLocalFileSlave() {
		return localFileSlave;
	}

	public void setLocalFileSlave(FileSlave localFileSlave) {
		this.localFileSlave = localFileSlave;
	}

	public boolean isExplicitIntermediate() {
		return explicitIntermediate;
	}

	public void setExplicitIntermediate(boolean explicitIntermediate) {
		this.explicitIntermediate = explicitIntermediate;
	}

	public void addToWord(CopyToWordEmitter word) {
		if(word != null)
			toWords.add(word);
	}

	public void addToWord(String word) {
		if(word == null)
			return;
		StringCopyToWordEmitter emitter = new StringCopyToWordEmitter();
		emitter.ingestObjectDefinitionLocation(this);
		emitter.addWord(word);
		toWords.add(emitter);
	}

	public boolean hasToWords() {
		return !toWords.isEmpty();
	}

	public void addFromWord(CopyFromWordEmitter word) {
		if(word != null)
			fromWords.add(word);
	}

	public void addFromWord(String word) {
		if(word == null)
			return;
		StringCopyFromWordEmitter emitter = new StringCopyFromWordEmitter();
		emitter.ingestObjectDefinitionLocation(this);
		emitter.addWord(word);
		fromWords.add(emitter);
	}

	protected boolean hasFromWords() {
		return !fromWords.isEmpty();
	}

	protected void copyTo(MachineStep.MachineStepInfo stepInfo, Machine machine, InFile source,
			String destination) throws PuppetException {
		if(execHost != null && explicitIntermediate)
			copyToIndirectly(stepInfo, machine, source, destination);
		else
			copyToDirectly(stepInfo, machine, source, destination);
	}

	private void copyToDirectly(MachineStep.MachineStepInfo stepInfo, Machine machine, InFile source,
			String destination) throws PuppetException {
		try(InFile inf = source) {
			List<String> argv = new LinkedList<String>();
			Consumer<String> sink = argv::add;
			CopyToInfo info = new CopyToInfo(stepInfo, machine, execHost, source, destination);
			for(CopyToWordEmitter emitter : toWords)
				emitter.buildArgv(info, sink);
			copy(stepInfo, argv, ioe -> new UploadIOPuppetException(machine, destination, ioe));
		}
		catch(IOException ioe) {
			throw new UploadIOPuppetException(machine, destination, ioe);
		}
	}

	private void copyToIndirectly(MachineStep.MachineStepInfo stepInfo, Machine machine, InFile source,
			String destination) throws PuppetException {
		try(InFile inf = source) {
			try(FileUtils.FileHolder temp = FileUtils.withTempFile(stepInfo, execHost)) {
				String tempPath = temp.getFile();
				source.copyTo(stepInfo, execHost, tempPath);
				List<String> argv = new LinkedList<String>();
				Consumer<String> sink = argv::add;
				IntermediateInFile intIn = new IntermediateInFile(inf, tempPath);
				CopyToInfo info = new CopyToInfo(stepInfo, machine, execHost, intIn, destination);
				for(CopyToWordEmitter emitter : toWords)
					emitter.buildArgv(info, sink);
				copy(stepInfo, argv, ioe -> new UploadIOPuppetException(machine, destination, ioe));
			}
		}
		catch(IOException ioe) {
			throw new UploadIOPuppetException(machine, destination, ioe);
		}
	}

	protected void copyFrom(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			OutFile destination) throws PuppetException {
		if(execHost != null && explicitIntermediate)
			copyFromIndirectly(stepInfo, machine, source, destination);
		else
			copyFromDirectly(stepInfo, machine, source, destination);
	}

	private void copyFromDirectly(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			OutFile destination) throws PuppetException {
		try(OutFile outf = destination) {
			List<String> argv = new LinkedList<String>();
			Consumer<String> sink = argv::add;
			CopyFromInfo info = new CopyFromInfo(stepInfo, machine, execHost, source, destination);
			for(CopyFromWordEmitter emitter : fromWords)
				emitter.buildArgv(info, sink);
			copy(stepInfo, argv, ioe -> new DownloadIOPuppetException(machine, source, ioe));
		}
		catch(IOException ioe) {
			throw new DownloadIOPuppetException(machine, source, ioe);
		}
	}

	private void copyFromIndirectly(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			OutFile destination) throws PuppetException {
		try(OutFile outf = destination) {
			try(FileUtils.FileHolder temp = FileUtils.withTempFile(stepInfo, execHost)) {
				String tempPath = temp.getFile();
				List<String> argv = new LinkedList<String>();
				Consumer<String> sink = argv::add;
				IntermediateOutFile intOut = new IntermediateOutFile(tempPath);
				CopyFromInfo info = new CopyFromInfo(stepInfo, machine, execHost, source, intOut);
				for(CopyFromWordEmitter emitter : fromWords)
					emitter.buildArgv(info, sink);
				copy(stepInfo, argv, ioe -> new DownloadIOPuppetException(machine, source, ioe));
				destination.copyFrom(stepInfo, execHost, tempPath);
			}
		}
		catch(IOException ioe) {
			throw new DownloadIOPuppetException(machine, source, ioe);
		}
	}

	private <ExceptT extends PuppetException & CommandOutputBuffer> void copy(MachineStep.MachineStepInfo stepInfo,
			Collection<String> argv, Function<? super IOException, ExceptT> onError) throws PuppetException {
		ExecControl xctl = ExecUtils.on(execHost).execute(stepInfo, execHost, argv, null, null, 0);
		ExecUtils.awaitSuccess(xctl, true, onError);
	}

	@Override
	protected void copyToImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			String destination) throws PuppetException {
		copyTo(stepInfo, machine, new FileInFile(source), destination);
	}

	@Override
	protected void copyToImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, InputStream source,
			String destination) throws PuppetException {
		copyTo(stepInfo, machine, new StreamInFile(stepInfo, source, localFileSlave, null), destination);
	}

	@Override
	protected void copyFromImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			String destination) throws PuppetException {
		copyFrom(stepInfo, machine, source, new FileOutFile(destination));
	}

	@Override
	protected void copyFromImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			OutputStream destination) throws PuppetException {
		copyFrom(stepInfo, machine, source, new StreamOutFile(stepInfo, destination, localFileSlave, null));
	}

}
