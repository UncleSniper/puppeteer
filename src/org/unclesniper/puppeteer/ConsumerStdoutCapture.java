package org.unclesniper.puppeteer;

import java.io.IOException;
import java.util.function.Consumer;

public class ConsumerStdoutCapture extends AbstractStdoutCapture {

	private Consumer<String> sink;

	public ConsumerStdoutCapture(Consumer<String> sink) {
		this.sink = sink;
	}

	public Consumer<String> getSink() {
		return sink;
	}

	public void setSink(Consumer<String> sink) {
		this.sink = sink;
	}

	@Override
	protected PuppetIORunnable captureStdoutImpl(MachineStep.MachineStepInfo info, ExecControl ctl) {
		try {
			CharReadEnd stdout = ctl.getStdoutCharReadEnd();
			LineReadEnd outLines = new LineReadEnd(stdout, true);
			LineReaderThread outReader = new LineReaderThread(outLines, sink);
			outReader.start();
			return () -> {
				IOException ioe = outReader.drain();
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
