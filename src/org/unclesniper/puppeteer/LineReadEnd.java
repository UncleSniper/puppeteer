package org.unclesniper.puppeteer;

import java.io.Closeable;
import java.io.IOException;

public class LineReadEnd implements Closeable {

	private CharReadEnd reader;

	private final char[] buffer = new char[128];

	private final StringBuilder builder = new StringBuilder();

	private boolean allowWinEOL;

	private boolean haveCR;

	private int fill;

	private int offset;

	public LineReadEnd(CharReadEnd reader, boolean allowWinEOL) {
		this.reader = reader;
		this.allowWinEOL = allowWinEOL;
	}

	public CharReadEnd getReader() {
		return reader;
	}

	public void setReader(CharReadEnd reader) {
		this.reader = reader;
	}

	public boolean isAllowWinEOL() {
		return allowWinEOL;
	}

	public void setAllowWinEOL(boolean allowWinEOL) {
		this.allowWinEOL = allowWinEOL;
	}

	private String flushLine() {
		String line = builder.toString();
		builder.setLength(0);
		return line;
	}

	public String readLine() throws IOException {
		for(;;) {
			int start = offset;
			for(; offset < fill; ++offset) {
				switch(buffer[offset]) {
					case '\n':
						if(offset > start) {
							if(haveCR)
								builder.append(buffer, start, offset - start - 1);
							else
								builder.append(buffer, start, offset - start);
						}
						haveCR = false;
						++offset;
						return flushLine();
					case '\r':
						if(allowWinEOL) {
							haveCR = true;
							break;
						}
					default:
						haveCR = false;
						break;
				}
			}
			if(offset > start) {
				if(haveCR)
					builder.append(buffer, start, offset - start - 1);
				else
					builder.append(buffer, start, offset - start);
			}
			int count = reader.read(buffer, 0, buffer.length);
			if(count <= 0)
				return builder.length() == 0 ? null : flushLine();
			offset = 0;
			fill = count;
		}
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

}
