package org.unclesniper.puppeteer;

public interface WordQuoter {

	void quoteIterable(Iterable<String> words, StringBuilder sink);

	void quoteArray(String[] words, StringBuilder sink);

	default String asString(Iterable<String> words) {
		StringBuilder sink = new StringBuilder();
		quoteIterable(words, sink);
		return sink.toString();
	}

	default String asString(String[] words) {
		StringBuilder sink = new StringBuilder();
		quoteArray(words, sink);
		return sink.toString();
	}

}
