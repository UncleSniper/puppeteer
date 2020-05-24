package org.unclesniper.puppeteer;

public interface PuppetPredicate<T> {

	boolean test(T object) throws PuppetException;

}
