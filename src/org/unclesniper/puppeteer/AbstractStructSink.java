package org.unclesniper.puppeteer;

public abstract class AbstractStructSink implements StructSink {

	public static final String DEFAULT_INDENT = "    ";

	private int level;

	private boolean fresh = true;

	private String indentString = AbstractStructSink.DEFAULT_INDENT;

	public AbstractStructSink() {
		this(0);
	}

	public AbstractStructSink(int level) {
		this.level = level < 0 ? 0 : level;
	}

	public String getIndentString() {
		return indentString;
	}

	public void setIndentString(String indentString) {
		this.indentString = indentString == null ? AbstractStructSink.DEFAULT_INDENT : indentString;
	}

	protected abstract void printImpl(String s);

	protected abstract void endlImpl();

	@Override
	public void print(String s) {
		if(s.length() == 0)
			return;
		if(fresh && indentString.length() > 0) {
			for(int i = 0; i < level; ++i)
				printImpl(indentString);
		}
		printImpl(s);
		fresh = false;
	}

	@Override
	public void endl() {
		endlImpl();
		fresh = true;
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
