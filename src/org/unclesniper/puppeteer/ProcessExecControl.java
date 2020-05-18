package org.unclesniper.puppeteer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.InterruptedIOException;
import java.nio.charset.StandardCharsets;

public class ProcessExecControl implements ExecControl {

	private final Process process;

	private final int flags;

	public ProcessExecControl(Process process, int flags) {
		this.process = process;
		this.flags = flags;
	}

	public Process getProcess() {
		return process;
	}

	public int getFlags() {
		return flags;
	}

	@Override
	public int wait(boolean nonZeroIsError) throws IOException {
		int status;
		try {
			status = process.waitFor();
		}
		catch(InterruptedException ie) {
			throw new InterruptedIOException(ie.getMessage());
		}
		if(nonZeroIsError && status != 0)
			throw new CommandFailedIOException(status);
		return status;
	}

	@Override
	public WriteEnd getStdinWriteEnd() {
		return new OutputStreamWriteEnd(process.getOutputStream());
	}

	@Override
	public CharWriteEnd getStdinCharWriteEnd() throws IOException {
		return new WriterCharWriteEnd(new OutputStreamWriter(process.getOutputStream(),
				(flags & ExecSlave.FL_STDIN_UTF8) == 0 ? StandardCharsets.ISO_8859_1 : StandardCharsets.UTF_8));
	}

	@Override
	public ReadEnd getStdoutReadEnd() throws IOException {
		return new InputStreamReadEnd(process.getInputStream());
	}

	@Override
	public CharReadEnd getStdoutCharReadEnd() throws IOException {
		return new ReaderCharReadEnd(new InputStreamReader(process.getInputStream(),
				(flags & ExecSlave.FL_STDOUT_UTF8) == 0 ? StandardCharsets.ISO_8859_1 : StandardCharsets.UTF_8));
	}

	@Override
	public ReadEnd getStderrReadEnd() throws IOException {
		return new InputStreamReadEnd(process.getErrorStream());
	}

	@Override
	public CharReadEnd getStderrCharReadEnd() throws IOException {
		return new ReaderCharReadEnd(new InputStreamReader(process.getErrorStream(),
				(flags & ExecSlave.FL_STDERR_UTF8) == 0 ? StandardCharsets.ISO_8859_1 : StandardCharsets.UTF_8));
	}

}
