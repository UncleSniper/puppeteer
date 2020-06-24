package org.unclesniper.puppeteer;

public class TextualExceptionSink implements ExceptionSink {

	private enum State {
		NONE,
		NATIVE_TRACE,
		PUPPET_TRACE,
		STDOUT,
		STDERR
	}

	private StructSink sink;

	private boolean nativeTrace;

	private int exceptionCount;

	private State state;

	public TextualExceptionSink(StructSink sink) {
		this(sink, true);
	}

	public TextualExceptionSink(StructSink sink, boolean nativeTrace) {
		this.sink = sink;
		this.nativeTrace = nativeTrace;
	}

	public StructSink getSink() {
		return sink;
	}

	public void setSink(StructSink sink) {
		this.sink = sink;
	}

	public boolean isNativeTrace() {
		return nativeTrace;
	}

	public void setNativeTrace(boolean nativeTrace) {
		this.nativeTrace = nativeTrace;
	}

	@Override
	public void beginChain() {}

	@Override
	public void beginException(Throwable t) {
		state = State.NONE;
		if(exceptionCount > 0)
			sink.print("Caused by: ");
		sink.println(t.toString());
		++exceptionCount;
	}

	@Override
	public void nativeFrame(StackTraceElement frame) {
		if(!nativeTrace)
			return;
		switch(state) {
			case NATIVE_TRACE:
				break;
			case PUPPET_TRACE:
			case STDOUT:
			case STDERR:
				sink.unindent();
			default:
				sink.indent();
				state = State.NATIVE_TRACE;
				break;
		}
		sink.print("at ");
		sink.println(frame.toString());
	}

	@Override
	public void puppetFrame(Traceable frame) {
		switch(state) {
			case PUPPET_TRACE:
				break;
			case NATIVE_TRACE:
			case STDOUT:
			case STDERR:
				sink.unindent();
			default:
				sink.println("Wire object backtrace:");
				sink.indent();
				state = State.PUPPET_TRACE;
				break;
		}
		sink.print(frame.getClass().getName());
		String location = frame.getTraceObjectDefinitionLocation();
		if(location != null && location.length() > 0)
			sink.print(" [defined at " + location + ']');
		sink.endl();
	}

	@Override
	public void stdoutLine(String line) {
		switch(state) {
			case STDOUT:
				break;
			case NATIVE_TRACE:
			case PUPPET_TRACE:
			case STDERR:
				sink.unindent();
			default:
				sink.println("Standard output:");
				sink.indent();
				state = State.STDOUT;
				break;
		}
		sink.println(line);
	}

	@Override
	public void stderrLine(String line) {
		switch(state) {
			case STDERR:
				break;
			case NATIVE_TRACE:
			case PUPPET_TRACE:
			case STDOUT:
				sink.unindent();
			default:
				sink.println("Standard error:");
				sink.indent();
				state = State.STDERR;
				break;
		}
		sink.println(line);
	}

	@Override
	public void endException(Throwable t) {
		switch(state) {
			case PUPPET_TRACE:
			case NATIVE_TRACE:
			case STDOUT:
			case STDERR:
				sink.unindent();
				break;
			default:
				break;
		}
		state = null;
	}

	@Override
	public void endChain() {}

}
