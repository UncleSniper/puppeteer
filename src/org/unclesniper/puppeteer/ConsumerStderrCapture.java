package org.unclesniper.puppeteer;

import java.io.IOException;
import java.util.function.Consumer;

public class ConsumerStderrCapture extends AbstractStderrCapture {

	private Consumer<String> sink;

	public ConsumerStderrCapture(Consumer<String> sink) {
		this.sink = sink;
	}

	public Consumer<String> getSink() {
		return sink;
	}

	public void setSink(Consumer<String> sink) {
		this.sink = sink;
	}

	@Override
	protected PuppetIORunnable captureStderrImpl(MachineStep.MachineStepInfo info, ExecControl ctl) {
		try {
			CharReadEnd stderr = ctl.getStderrCharReadEnd();
			LineReadEnd errLines = new LineReadEnd(stderr, true);
			LineReaderThread errReader = new LineReaderThread(errLines, sink);
			errReader.start();
			return () -> {
				IOException ioe = errReader.drain();
				if(ioe != null)
					throw ioe;
			};
		}
		catch(IOException ioe) {
			return () -> {
				throw ioe;
			};
		}
	}

}
