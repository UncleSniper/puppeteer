package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("bashQuoter")
public class BashWordQuoter extends AbstractWordQuoter {

	public static final BashWordQuoter instance = new BashWordQuoter();

	public BashWordQuoter() {}

	private static void quote(String word, boolean first, StringBuilder sink) {
		if(!first)
			sink.append(' ');
		sink.append('\'');
		int old = 0, length = word.length();
		while(old < length) {
			int pos = word.indexOf('\'', old);
			if(pos < 0)
				break;
			if(pos > old)
				sink.append(word, old, pos);
			sink.append("'\\''");
			old = pos + 1;
		}
		if(old < length)
			sink.append(word, old, length);
		sink.append('\'');
	}

	@Override
	public void quoteIterable(Iterable<String> words, StringBuilder sink) {
		boolean first = true;
		for(String word : words) {
			BashWordQuoter.quote(word, first, sink);
			first = false;
		}
	}

	@Override
	public void quoteArray(String[] words, StringBuilder sink) {
		boolean first = true;
		for(String word : words) {
			BashWordQuoter.quote(word, first, sink);
			first = false;
		}
	}

}
