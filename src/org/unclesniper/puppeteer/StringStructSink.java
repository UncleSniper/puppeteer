package org.unclesniper.puppeteer;

public class StringStructSink implements StructSink {

	public static final String ENDL = System.getProperty("line.separator");

	public static final String DEFAULT_INDENT = "    ";

	private final StringBuilder builder;

	private int level;

	private boolean fresh = true;

	private String indentString;

	public StringStructSink() {
		this(null, 0);
	}

	public StringStructSink(StringBuilder builder) {
		this(builder, 0);
	}

	public StringStructSink(StringBuilder builder, int level) {
		this.builder = builder == null ? new StringBuilder() : builder;
		this.level = level < 0 ? 0 : level;
	}

	public StringBuilder getBuilder() {
		return builder;
	}

	public String getIndentString() {
		return indentString;
	}

	public void setIndentString(String indentString) {
		this.indentString = indentString == null ? StringStructSink.DEFAULT_INDENT : indentString;
	}

	private void doIndent() {
		for(int i = 0; i < level; ++i)
			builder.append(indentString);
	}

	@Override
	public String toString() {
		return builder.toString();
	}

	@Override
	public void print(String s) {
		if(s.length() == 0)
			return;
		if(fresh)
			doIndent();
		builder.append(s);
		fresh = false;
	}

	@Override
	public void endl() {
		builder.append(StringStructSink.ENDL);
	}

	@Override
	public void indent() {
		++level;
	}

	@Override
	public void unindent() {
		if(level == 0)
			throw new IllegalStateException("Already at indent level 0");
		--level;
	}

}
