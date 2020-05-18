package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;

public class ExecUtils {

	private ExecUtils() {}

	public static ExecControl useArray(ExecSlave slave, Machine machine, Collection<String> argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException {
		return slave.execute(machine, argv.toArray(new String[argv.size()]), workdir, environ, flags);
	}

	public static ExecControl useCollection(ExecSlave slave, Machine machine, String[] argv, String workdir,
			Map<String, String> environ, int flags) throws PuppetException {
		List<String> list = new ArrayList<String>(argv.length);
		for(String word : argv)
			list.add(word);
		return slave.execute(machine, list, workdir, environ, flags);
	}

	public static ExecSlave on(Machine machine) {
		return machine == null ? LocalExecSlave.instance : machine.getExecSlave();
	}

	public static <ExceptT extends Exception & CommandOutputBuffer> void awaitSuccess(ExecControl xctl,
			boolean allowWinEOL, Function<? super IOException, ? extends ExceptT> onError) throws ExceptT {
		List<String> outBuffer = new LinkedList<String>();
		List<String> errBuffer = new LinkedList<String>();
		try(CharReadEnd stdout = xctl.getStdoutCharReadEnd()) {
			try(CharReadEnd stderr = xctl.getStderrCharReadEnd()) {
				LineReadEnd outLines = new LineReadEnd(stdout, allowWinEOL);
				LineReaderThread outReader = new LineReaderThread(outLines, outBuffer::add);
				LineReadEnd errLines = new LineReadEnd(stderr, allowWinEOL);
				LineReaderThread errReader = new LineReaderThread(errLines, errBuffer::add);
				outReader.start();
				errReader.start();
				IOException outErr = outReader.drain();
				IOException errErr = errReader.drain();
				if(outErr != null)
					throw outErr;
				if(errErr != null)
					throw errErr;
				xctl.wait(true);
			}
		}
		catch(IOException ioe) {
			ExceptT inner = onError.apply(ioe);
			for(String line : outBuffer)
				inner.addStdoutLine(line);
			for(String line : errBuffer)
				inner.addStderrLine(line);
			throw inner;
		}
	}

}
