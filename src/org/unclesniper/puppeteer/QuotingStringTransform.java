package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("quotingStringTransform")
public class QuotingStringTransform implements StringTransform {

	private WordQuoter quoter;

	public QuotingStringTransform() {}

	public QuotingStringTransform(WordQuoter quoter) {
		this.quoter = quoter;
	}

	public WordQuoter getQuoter() {
		return quoter;
	}

	public void setQuoter(WordQuoter quoter) {
		this.quoter = quoter;
	}

	public String transformString(String original) {
		return quoter == null ? original : quoter.asString(new String[] {original});
	}

}
