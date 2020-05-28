package org.unclesniper.puppeteer;

public interface NetworkStepStringSource {

	void buildString(NetworkStep.NetworkStepInfo info, StringBuilder sink) throws PuppetException;

}
