package org.unclesniper.puppeteer;

public interface ExceptionSink {

	void beginChain();

	void beginException(Throwable t);

	void nativeFrame(StackTraceElement frame);

	void puppetFrame(Traceable frame);

	void stdoutLine(String line);

	void stderrLine(String line);

	void endException(Throwable t);

	void endChain();

	public static void describe(Throwable t, ExceptionSink sink) {
		if(t == null)
			return;
		sink.beginChain();
		while(t != null) {
			sink.beginException(t);
			for(StackTraceElement frame : t.getStackTrace())
				sink.nativeFrame(frame);
			if(t instanceof PuppetException) {
				for(Traceable frame : ((PuppetException)t).getPuppetFrames())
					sink.puppetFrame(frame);
			}
			CommandOutputHolder output = null;
			if(t instanceof CommandOutputHolder)
				output = (CommandOutputHolder)t;
			else if(t instanceof CommandOutputHolderHolder)
				output = ((CommandOutputHolderHolder)t).getCommandOutput();
			if(output != null) {
				for(String line : output.getStdoutLines())
					sink.stdoutLine(line);
				for(String line : output.getStderrLines())
					sink.stderrLine(line);
			}
			sink.endException(t);
			t = t.getCause();
		}
		sink.endChain();
	}

}
