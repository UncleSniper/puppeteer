package org.unclesniper.puppeteer;

public class StringStructSink extends AbstractStructSink {

	public static final String ENDL = System.getProperty("line.separator");

	private final StringBuilder builder;

	public StringStructSink() {
		this(null, 0);
	}

	public StringStructSink(StringBuilder builder) {
		this(builder, 0);
	}

	public StringStructSink(StringBuilder builder, int level) {
		super(level);
		this.builder = builder == null ? new StringBuilder() : builder;
	}

	public StringBuilder getBuilder() {
		return builder;
	}

	@Override
	protected void printImpl(String s) {
		builder.append(s);
	}

	@Override
	protected void endlImpl() {
		builder.append(StringStructSink.ENDL);
	}

	@Override
	public String toString() {
		return builder.toString();
	}

}
