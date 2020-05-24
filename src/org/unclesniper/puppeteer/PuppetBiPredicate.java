package org.unclesniper.puppeteer;

public interface PuppetBiPredicate<T, U> {

	boolean test(T t, U u) throws PuppetException;

}
