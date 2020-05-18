package org.unclesniper.puppeteer;

import java.io.IOException;

public interface ExecControl {

	int wait(boolean nonZeroIsError) throws IOException;

	WriteEnd getStdinWriteEnd() throws IOException;

	CharWriteEnd getStdinCharWriteEnd() throws IOException;

	ReadEnd getStdoutReadEnd() throws IOException;

	CharReadEnd getStdoutCharReadEnd() throws IOException;

	ReadEnd getStderrReadEnd() throws IOException;

	CharReadEnd getStderrCharReadEnd() throws IOException;

}
